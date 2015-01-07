package il.ac.hit.couponsproject.model.dao.logic;

import java.util.Collection;

import il.ac.hit.couponsproject.model.dto.Coupon;
import il.ac.hit.couponsproject.model.exception.CouponException;

/**
 * This DAO interface is declaring some basic operations about Coupons systems which interacting with any kind of DB.</br>
 * Classes which about to implement this interface will need to provide all the services that this interface is
 * offering.
 * 
 * @author Idan Faibish
 * @author Shimi Karaso
 *
 */
public interface ICouponDAO {
	/**
	 * This method will return all the coupons that are stored in the underlying DB
	 * 
	 * @return a Collection of the coupons
	 * @throws CouponException
	 */
	public Collection<Coupon> getCoupons() throws CouponException;


	/**
	 * This method is getting an id of a specific coupon and returns his corresponding coupon from the underlying DB
	 * 
	 * @param id
	 *            represents the id of the coupon you want to get from the DB
	 * @return returns the corresponding Coupon object of the given id
	 * @throws CouponException
	 */
	public Coupon getCoupon(int id) throws CouponException;


	/**
	 * This method is getting a Coupon object and adding him to the underlying DB
	 * 
	 * @param coupon
	 *            Coupon object that you want to add to the underlying DB
	 * @throws CouponException
	 */
	public void addCoupon(Coupon coupon) throws CouponException;

	/**
	 * This method is getting an id of a specific coupon and deletes his corresponding coupon from the underlying DB
	 * 
	 * @param id
	 *            represents the id of the coupon you want to delete from the DB
	 * @throws CouponException
	 */
	public void deleteCoupon(int id) throws CouponException;

	/**
	 * This method is getting a Coupon object and updating his contents in the underlying DB
	 * 
	 * @param coupon
	 *            Coupon object that you want to update his contents in the underlying DB
	 * @throws CouponException
	 */
	public void updateCoupon(Coupon coupon) throws CouponException;

	/**
	 * This method returns at most 3 of the closest coupons according to the given longitude and latitude values
	 * 
	 * @param longitude
	 *            represents the longitude value
	 * @param latitude
	 *            represents the latitude value
	 * @return a Collection of at most 3 closest coupons
	 * @throws CouponException
	 */
	public Collection<Coupon> getThreeClosestCoupons(double longitude, double latitude) throws CouponException;

	/**
	 * This method returns all the non-expired coupons <br/>
	 * (according to each expiration-date compared with the current date and time)
	 * 
	 * @return a Collection of all the non-expired coupons in the underlying DB
	 * @throws CouponException
	 */
	public Collection<Coupon> getNonExpiredCoupons() throws CouponException;

}
