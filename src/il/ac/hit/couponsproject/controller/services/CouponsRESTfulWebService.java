package il.ac.hit.couponsproject.controller.services;

import il.ac.hit.couponsproject.configuration.constants.IConstants;
import il.ac.hit.couponsproject.controller.Controller;
import il.ac.hit.couponsproject.model.dao.impl.hibernate.HibernateCouponDAO;
import il.ac.hit.couponsproject.model.dao.logic.ICouponDAO;
import il.ac.hit.couponsproject.model.dto.Coupon;
import il.ac.hit.couponsproject.model.exception.CouponException;

import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * This RESTful Web Service is responsible for handling some coupons' GET requests from the client.
 * 
 * @author Idan Faibish
 * @author Shimi Karaso
 */
@Path("/CouponsService")
public class CouponsRESTfulWebService {

	private ICouponDAO couponsDAO = HibernateCouponDAO.getInstance();

	/**
	 * this method is actually storing in a collection all the coupons from the DB. and then sending this collection as
	 * JSON back to the client
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/coupons")
	public Response retrieveCoupons() {
		try {
			Collection<Coupon> list = couponsDAO.getCoupons();
			Controller.LOGGER.info("succeeded to retrieve the coupons");
			return Response.ok(list).build();

		} catch (CouponException e) {
			Controller.LOGGER.error("failed to retrieve the coupons");
			// return an empty JSON array
			return Response.ok(IConstants.EMPTY_JSON_ARRAY).build();
		}
	}

	/**
	 * this method is actually getting a id of a coupon(according to the PathParam),
	 * retrieves the coupon from the DB, and then sending this coupon as JSON back to the client
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/coupons/{id}")
	public Response retrieveCouponById(@PathParam("id") int id) {
		try {
			Coupon resultCoupon = couponsDAO.getCoupon(id);
			Controller.LOGGER.info("succeeded to get coupon with id: " + id);
			return Response.ok(resultCoupon).build();
		} catch (CouponException e) {
			Controller.LOGGER.error("failed to get coupon with id: " + id);
			//return empty JSON object
			return Response.ok(IConstants.EMPTY_JSON_OBJECT).build();
		}
	}
}
