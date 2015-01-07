package il.ac.hit.couponsproject.controller.actions.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import il.ac.hit.couponsproject.configuration.constants.IConstants;
import il.ac.hit.couponsproject.controller.actions.logic.IAction;
import il.ac.hit.couponsproject.model.dto.Coupon;

/**
 * This action is responsible for updating the favorites coupons list of the current session<br/>
 * (according to the current Date & Time)<br/>
 * and eventually it retrieves this list.<br/>
 * <em><strong>(especially for showing the stars feature in guestPage.jsp)</strong></em>
 * 
 * @author Idan Faibish
 * @author Shimi Karaso
 * @see IAction
 */
public class UpdateAndRetrieveFavoritesCouponsAction implements IAction {

	/**
	 * this method returns the name of this action: "update-and-retrieve-favorites-coupons-action"
	 */
	@Override
	public String getName() {
		return IConstants.UPDATE_AND_RETRIEVE_FAVORITES_COUPONS_ACTION;
	}

	/**
	 * this method is actually retrieves the current favorites coupons collection of the session, and then it sifts the
	 * coupons of this collection(into a new collection) according to the current Date & Time (only the non-expired will
	 * be in the new collection). eventually the method sets the new collection as the favorites coupons collection of the session,
	 * parse this collection as string of JSON and writing it in the response
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
		response.setContentType("application/json; charset=UTF-8");
		HttpSession session = request.getSession();
		Collection<Coupon> currentSessionCouponsList = (Collection<Coupon>) session.getAttribute(IConstants.ATTR_FAVORITES_COUPONS_LIST);
		Collection<Coupon> nonExpiredCouponsList = new ArrayList<Coupon>();
		Date currentTime = new Date();
		if (currentSessionCouponsList == null) {
			response.getWriter().print(IConstants.EMPTY_JSON_OBJECT);
		} else {
			Iterator<Coupon> iterator = currentSessionCouponsList.iterator();
			while (iterator.hasNext()) {
				Coupon currentCoupon = iterator.next();
				//assures the coupons won't be expired in the nonExpiredCouponsList
				if (currentCoupon.getExpirationDate().after(currentTime)) {
					nonExpiredCouponsList.add(currentCoupon);
				}
			}
			session.setAttribute(IConstants.ATTR_FAVORITES_COUPONS_LIST, nonExpiredCouponsList);
			response.getWriter().print(new Gson().toJson(nonExpiredCouponsList));
		}
	}
}
