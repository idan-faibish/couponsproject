package il.ac.hit.couponsproject.controller.actions.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
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
 * This action is responsible for updating the favorites coupons list of the current session <br/>
 * (according to the current Date & Time)<br/>
 * and forwards to a page that shows them.
 * 
 * @author Idan Faibish
 * @author Shimi Karaso
 * @see IAction
 */
public class ForwardWithUpdatedFavoritesCouponsAction implements IAction {

	private ICouponDAO couponsDAO = HibernateCouponDAO.getInstance();

	/**
	 * this method returns the name of this action: "forward-with-updated-favorites-coupons-action"
	 */
	@Override
	public String getName() {
		return IConstants.FORWARD_WITH_UPDATED_FAVORITES_COUPONS_ACTION;
	}

	/**
	 * this method is actually retrieves the current favorites coupons collection of the session, and then it sifts the
	 * coupons of this collection (into a new collection) according to the current Date & Time (only the non-expired
	 * will be in the new collection). in addition, the coupons in the new collection will be the most updated version
	 * of those coupons. eventually the method sets the new collection as the favorites coupons list of the session and
	 * forwards the execution to the favoritesCoupons.jsp to show them to the user
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
		HttpSession session = request.getSession();
		try {
			Collection<Coupon> couponsList = couponsDAO.getCoupons();
			Collection<Coupon> favoritesList = (Collection<Coupon>) session.getAttribute(IConstants.ATTR_FAVORITES_COUPONS_LIST);
			Collection<Coupon> updatedFavoritesList = new ArrayList<Coupon>();
			Date currentTime = new Date();
			
			if (favoritesList != null) {
				Iterator<Coupon> favoritesIterator = favoritesList.iterator();
				while (favoritesIterator.hasNext()) {
					Coupon currentFavorite = favoritesIterator.next();
					Iterator<Coupon> couponsIterator = couponsList.iterator();
					while (couponsIterator.hasNext()) {
						Coupon currentCoupon = couponsIterator.next();
						//assures the coupons won't be expired in the updatedFavoritesList
						if (currentCoupon.getId() == currentFavorite.getId()
								&& currentCoupon.getExpirationDate().after(currentTime)) {
							updatedFavoritesList.add(currentCoupon);
						}
					}
				}
			}
			Controller.LOGGER.info("successfully updated the favorites coupons and remove the expired coupons, of session: " + session.getId());
			session.setAttribute(IConstants.ATTR_FAVORITES_COUPONS_LIST, updatedFavoritesList);
		} catch (CouponException e) {
			// the favorites coupons of the session stays the same as it was
			Controller.LOGGER.error("failed to update the favorites coupons or remove the expired coupons, of session: " + session.getId()+" (it will stay as it was)");

		}
		RequestDispatcher rd = request.getRequestDispatcher(IConstants.FAVORITES_COUPONS_PAGE_PATH);
		rd.forward(request, response);
	}
}
