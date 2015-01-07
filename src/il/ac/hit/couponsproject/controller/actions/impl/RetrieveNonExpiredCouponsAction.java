package il.ac.hit.couponsproject.controller.actions.impl;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import il.ac.hit.couponsproject.configuration.constants.IConstants;
import il.ac.hit.couponsproject.controller.Controller;
import il.ac.hit.couponsproject.controller.actions.logic.IAction;
import il.ac.hit.couponsproject.model.dao.impl.hibernate.HibernateCouponDAO;
import il.ac.hit.couponsproject.model.dao.logic.ICouponDAO;
import il.ac.hit.couponsproject.model.dto.Coupon;
import il.ac.hit.couponsproject.model.exception.CouponException;

/**
 * This action is responsible for retrieving all the non-expired coupons from DB.<br/>
 * (according to the current Date & Time)
 * 
 * @author Idan Faibish
 * @author Shimi Karaso
 * @see IAction
 */
public class RetrieveNonExpiredCouponsAction implements IAction {

	private ICouponDAO couponsDAO = HibernateCouponDAO.getInstance();

	/**
	 * this method returns the name of this action: "retrieve-non-expired-coupons-action"
	 */
	@Override
	public String getName() {
		return IConstants.RETRIEVE_NON_EXPIRED_COUPONS_ACTION;
	}

	/**
	 * this method is actually storing in a Collection all the non-expired coupons from the DB. and then parsing the
	 * list as a string of JSON and writing it in the response
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
		try {
			Collection<Coupon> nonExpiredCoupons = couponsDAO.getNonExpiredCoupons();
			Controller.LOGGER.info("succeeded to retrieve the non-expired coupons");
			response.getWriter().print(new Gson().toJson(nonExpiredCoupons));
		} catch (CouponException e) {
			Controller.LOGGER.error("failed to retrieve the non-expired coupons");
			response.getWriter().print(IConstants.EMPTY_JSON_OBJECT);
		}
	}
}
