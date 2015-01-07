package il.ac.hit.couponsproject.controller.actions.impl;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import il.ac.hit.couponsproject.configuration.constants.IConstants;
import il.ac.hit.couponsproject.controller.Controller;
import il.ac.hit.couponsproject.controller.actions.logic.IAction;
import il.ac.hit.couponsproject.model.dao.impl.hibernate.HibernateCouponDAO;
import il.ac.hit.couponsproject.model.dao.logic.ICouponDAO;
import il.ac.hit.couponsproject.model.dto.Coupon;
import il.ac.hit.couponsproject.model.exception.CouponException;

/**
 * This action is responsible for retrieving the three closest coupons according to given longitude and latitude, and
 * forwards to a page that shows them.
 * 
 * @author Idan Faibish
 * @author Shimi Karaso
 * @see IAction
 */
public class RetrieveThreeClosestCouponsAction implements IAction {

	private ICouponDAO couponsDAO = HibernateCouponDAO.getInstance();

	/**
	 * this method returns the name of this action: "retrieve-three-closest-coupons-action"
	 */
	@Override
	public String getName() {
		return IConstants.RETRIEVE_THREE_CLOSEST_COUPONS_ACTION;
	}

	/**
	 * this method is actually retrieving the three closest coupons to the user from the DB and store them in a
	 * collection (according to parameters that came in the HTTP request). and then it sets this collection as an
	 * attribute on the request object and forwards the execution to the closestCoupons.jsp to show them to the user
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
		String longitude = request.getParameter(IConstants.LONGITUDE);
		String latitude = request.getParameter(IConstants.LATITUDE);
		try {
			Collection<Coupon> threeClosestCoupons = couponsDAO.getThreeClosestCoupons(Double.parseDouble(longitude),
					Double.parseDouble(latitude));
			Controller.LOGGER.info("succeeded to retrieve the three closest coupons to longitude: " + longitude
					+ " latitude: " + latitude);
			request.setAttribute(IConstants.ATTR_THREE_CLOSEST_COUPONS, threeClosestCoupons);
		} catch (CouponException e) {
			// the forward to closestCoupons.jsp page is taking care of the error (inside the closestCoupons.jsp page)
			Controller.LOGGER.error("failed to retrieve the three closest coupons (DB problem) to longitude: "
					+ longitude + " latitude: " + latitude);
			request.removeAttribute(IConstants.ATTR_THREE_CLOSEST_COUPONS);
		}
		RequestDispatcher rd = request.getRequestDispatcher(IConstants.CLOSEST_COUPONS_PAGE_PATH);
		rd.forward(request, response);
	}
}
