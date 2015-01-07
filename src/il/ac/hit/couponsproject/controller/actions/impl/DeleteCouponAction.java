package il.ac.hit.couponsproject.controller.actions.impl;

import java.io.IOException;

import il.ac.hit.couponsproject.configuration.constants.IConstants;
import il.ac.hit.couponsproject.controller.Controller;
import il.ac.hit.couponsproject.controller.actions.logic.IAction;
import il.ac.hit.couponsproject.model.dao.impl.hibernate.HibernateCouponDAO;
import il.ac.hit.couponsproject.model.dao.logic.ICouponDAO;
import il.ac.hit.couponsproject.model.exception.CouponException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This action is responsible for deleting a specific coupon from the DB.
 * 
 * @author Idan Faibish
 * @author Shimi Karaso
 * @see IAction
 */
public class DeleteCouponAction implements IAction {

	private ICouponDAO couponsDAO = HibernateCouponDAO.getInstance();

	/**
	 * this method returns the name of this action: "delete-coupon-action"
	 */
	@Override
	public String getName() {
		return IConstants.DELETE_COUPON_ACTION;
	}

	/**
	 * this method is actually getting an id of a specific coupon and tries to delete his corresponding coupon from the
	 * DB. if the operation has finished successfully or not we write an appropriate message in the response
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
		String id = request.getParameter(IConstants.ID);
		try {
			couponsDAO.deleteCoupon(Integer.parseInt(id));
			Controller.LOGGER.info("succeeded to delete coupon with id: " + id);
			response.getWriter().print(IConstants.SUCCESS);
		} catch (CouponException e) {
			Controller.LOGGER.error("failed to delete coupon with id: " + id + " (DB problem)");
			response.getWriter().print(IConstants.FAILURE);
		}
	}
}
