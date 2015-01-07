package il.ac.hit.couponsproject.controller.actions.impl;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;

import il.ac.hit.couponsproject.configuration.constants.IConstants;
import il.ac.hit.couponsproject.controller.Controller;
import il.ac.hit.couponsproject.controller.actions.logic.IAction;
import il.ac.hit.couponsproject.model.dao.impl.hibernate.HibernateCouponDAO;
import il.ac.hit.couponsproject.model.dao.logic.ICouponDAO;
import il.ac.hit.couponsproject.model.dto.Coupon;
import il.ac.hit.couponsproject.model.exception.CouponException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This action is responsible for adding a coupon to the DB.
 * 
 * @author Idan Faibish
 * @author Shimi Karaso
 * @see IAction
 */
public class AddCouponAction implements IAction {

	private ICouponDAO couponsDAO = HibernateCouponDAO.getInstance();

	/**
	 * this method returns the name of this action: "add-coupon-action"
	 */
	@Override
	public String getName() {
		return IConstants.ADD_COUPON_ACTION;
	}

	/**
	 * this method is actually building a Coupon object(according to parameters that came in the HTTP request) and tries
	 * to add him to the DB. if the operation has finished successfully or not we write an appropriate message in the
	 * response
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
		Coupon couponToAdd = null;
		try {
			String id 				= request.getParameter(IConstants.ID);
			String name			 	= request.getParameter(IConstants.NAME);
			String description 		= request.getParameter(IConstants.DESCRIPTION);
			String category 		= request.getParameter(IConstants.CATEGORY);
			String value 			= request.getParameter(IConstants.VALUE);
			String percentDiscount  = request.getParameter(IConstants.PERCENT_DISCOUNT);
			String expirationDate	= request.getParameter(IConstants.EXPIRATION_DATE);
			String picURL 			= request.getParameter(IConstants.PICTURE_URL);
			String longitude 		= request.getParameter(IConstants.LONGITUDE);
			String latitude 		= request.getParameter(IConstants.LATITUDE);
			couponToAdd = new Coupon();
			couponToAdd.setId(Integer.parseInt(id));
			couponToAdd.setName(name);
			couponToAdd.setDescription(description);
			couponToAdd.setCategory(category);
			couponToAdd.setValue(Double.parseDouble(value));
			couponToAdd.setPercentDiscount(Integer.parseInt(percentDiscount));
			Date date = IConstants.SIMPLE_FORMAT.parse(expirationDate);
			couponToAdd.setExpirationDate(new Timestamp(date.getTime()));
			couponToAdd.setPicURL(picURL);
			couponToAdd.setLongitude(Double.parseDouble(longitude));
			couponToAdd.setLatitude(Double.parseDouble(latitude));

			couponsDAO.addCoupon(couponToAdd);
			Controller.LOGGER.info("succeeded to add coupon with id: " + couponToAdd.getId());
			response.getWriter().print(IConstants.SUCCESS);
		} catch (CouponException | ParseException e) {
			Controller.LOGGER.error("failed to add coupon with id: " + couponToAdd.getId());
			response.getWriter().print(IConstants.FAILURE);
		}
	}
}
