package il.ac.hit.couponsproject.controller.actions.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import il.ac.hit.couponsproject.configuration.constants.IConstants;
import il.ac.hit.couponsproject.controller.Controller;
import il.ac.hit.couponsproject.controller.actions.logic.IAction;
import il.ac.hit.couponsproject.model.dto.Coupon;

/**
 * This action is responsible for deleting a coupon from the favorite list of the current session
 * 
 * @author Idan Faibish
 * @author Shimi Karaso
 * @see IAction
 */
public class DeleteFavoriteCouponAction implements IAction {

	/**
	 * this method returns the name of this action: "delete-favorite-coupon-action"
	 */
	@Override
	public String getName() {
		return IConstants.DELETE_FAVORITE_COUPON_ACTION;
	}

	/**
	 * this method is actually getting a id of a coupon(according to a parameter that came in the HTTP request),
	 * retrieves the current favorites list of the session, and then delete from the list the coupon with this id. if
	 * the operation has finished successfully or not we write an appropriate message in the response
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
		int id = Integer.parseInt(request.getParameter(IConstants.ID));
		HttpSession session = request.getSession();
		List<Coupon> couponsList = (List<Coupon>) session.getAttribute(IConstants.ATTR_FAVORITES_COUPONS_LIST);
		if (couponsList == null) {
			Controller.LOGGER.error("failed to delete coupon with id: " + id
					+ " from favorites, since there are no favorites at all");
			response.getWriter().print(IConstants.FAILURE);
		//the list is indeed exists
		} else {
			boolean isExist = false;
			int couponsListSize = couponsList.size();
			for (int i = 0; i < couponsListSize; ++i) {
				if (couponsList.get(i).getId() == id) {
					couponsList.remove(i);
					isExist = true;
					break;
				}
			}
			if (isExist == true) {
				Controller.LOGGER.info("succeeded to delete coupon with id: " + id + " from favorites of session: "
						+ session.getId());
				response.getWriter().print(IConstants.SUCCESS);
			} else {
				Controller.LOGGER.error("failed to delete coupon with id: " + id + " from favorites of session: "
						+ session.getId() + " , since he is not there");
				response.getWriter().print(IConstants.FAILURE);
			}
		}
	}

}
