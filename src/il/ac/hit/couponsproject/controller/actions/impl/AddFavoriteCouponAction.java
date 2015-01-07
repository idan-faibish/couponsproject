package il.ac.hit.couponsproject.controller.actions.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import il.ac.hit.couponsproject.configuration.constants.IConstants;
import il.ac.hit.couponsproject.controller.Controller;
import il.ac.hit.couponsproject.controller.actions.logic.IAction;
import il.ac.hit.couponsproject.model.dao.impl.hibernate.HibernateCouponDAO;
import il.ac.hit.couponsproject.model.dao.logic.ICouponDAO;
import il.ac.hit.couponsproject.model.dto.Coupon;
import il.ac.hit.couponsproject.model.exception.CouponException;

/**
 * This action is responsible for adding a coupon to the favorite list of the current session.
 * 
 * @author Idan Faibish
 * @author Shimi Karaso
 * @see IAction
 */
public class AddFavoriteCouponAction implements IAction {

	private ICouponDAO couponsDAO = HibernateCouponDAO.getInstance();

	/**
	 * this method returns the name of this action: "add-favorite-coupon-action"
	 */
	@Override
	public String getName() {
		return IConstants.ADD_FAVORITE_COUPON_ACTION;
	}

	/**
	 * this method is actually getting a id of a coupon(according to a parameter that came in the HTTP request), it
	 * retrieves the coupon from the DB and add him to the current list of this session (if this list exists and in case
	 * the list doesn't already have this coupon inside him). if the operation has finished successfully or not we write
	 * an appropriate message in the response
	 * 
	 * @param request
	 *            the HttpServletRequest object from the client
	 * @param response
	 *            response the HttpServletResponse object that will be sent to the client
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	public void perform(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain");
		HttpSession session = request.getSession();
		try {
			int id = Integer.parseInt(request.getParameter(IConstants.ID));
			Coupon couponToAdd = couponsDAO.getCoupon(id);
			List<Coupon> couponsList = (List<Coupon>) session.getAttribute(IConstants.ATTR_FAVORITES_COUPONS_LIST);
			// creation of new list
			if (couponsList == null) {
				couponsList = new ArrayList<Coupon>();
				couponsList.add(couponToAdd);
				session.setAttribute(IConstants.ATTR_FAVORITES_COUPONS_LIST, couponsList);
				Controller.LOGGER.info("succeeded to add coupon with id: " + couponToAdd.getId()
						+ " to favorites of session: " + session.getId());
				response.getWriter().print(IConstants.SUCCESS);
			// the list is already exists
			} else {
				Iterator<Coupon> iterator = couponsList.iterator();
				//isExist means: if the coupon we trying to add is already exists in the list so true, otherwise false
				boolean isExist = false;
				while (iterator.hasNext()) {
					if (iterator.next().getId() == id) {
						isExist = true;
						break;	
					}
				}
				if (isExist == false) {
					couponsList.add(couponToAdd);
					Controller.LOGGER.info("succeeded to add coupon with id: " + couponToAdd.getId()
							+ " to favorites of session: " + session.getId());
					response.getWriter().print(IConstants.SUCCESS);
				} else {
					Controller.LOGGER.error("failed to add coupon with id: " + couponToAdd.getId()
							+ " to favorites of session: " + session.getId() + ", since this coupon is already there");
					response.getWriter().print(IConstants.FAILURE);
				}
			}
		} catch (CouponException e) {
			Controller.LOGGER.error("failed to add coupon with id: " + request.getParameter(IConstants.ID)
					+ " to favorites of session: " + session.getId() + " (DB problem)");
			response.getWriter().print(IConstants.FAILURE);
		}
	}
}
