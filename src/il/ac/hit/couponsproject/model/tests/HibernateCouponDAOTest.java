package il.ac.hit.couponsproject.model.tests;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;

import il.ac.hit.couponsproject.model.dao.impl.hibernate.HibernateCouponDAO;
import il.ac.hit.couponsproject.model.dto.Coupon;
import il.ac.hit.couponsproject.model.exception.CouponException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * JUnit tests for some methods of HibernateCouponDAO Class
 * 
 * @author Idan Faibish
 * @author Shimi Karaso
 */
public class HibernateCouponDAOTest {

	HibernateCouponDAO dao = HibernateCouponDAO.getInstance();
	Coupon couponToAdd = null;
	Coupon retrievedCoupon = null;

	/**
	 * adding new coupon to the DB for further processing in the test methods
	 */
	@Before
	public void setUp() throws Exception {
		couponToAdd = new Coupon();
		couponToAdd.setId(174);
		couponToAdd.setName("firstCoupon");
		couponToAdd.setDescription("firstCoupon");
		couponToAdd.setCategory("firstCoupon");
		couponToAdd.setValue(100);
		couponToAdd.setPercentDiscount(50);
		couponToAdd.setExpirationDate(new Timestamp(new Date().getTime()));
		couponToAdd.setPicURL("http://firstCoupon");
		couponToAdd.setLongitude(12);
		couponToAdd.setLatitude(12);
		dao.addCoupon(couponToAdd);
	}

	/** deleting the coupon that was added in the setUp method */
	@After
	public void tearDown() throws Exception {
		dao.deleteCoupon(174);
		couponToAdd = null;
	}

	/** at this moment there are 21 coupons in the DB (with the the coupon we added in the setUp method) */
	@Test
	public void testGetCoupons() throws CouponException {
		Collection<Coupon> coupons = (Collection<Coupon>) dao.getCoupons();
		assertTrue(coupons.size() == 21);
	}

	/** checks longitude of a specific coupon from the DB (with id = 100) */
	@Test
	public void testGetCoupon() throws CouponException {

		Coupon couponFromTheDB = dao.getCoupon(100);
		assertTrue(couponFromTheDB.getLongitude() == 12.222);
	}

	/**
	 * try to retrieve the coupon we added in the setUp method (and if its true, we actually succeeded in adding him in
	 * the first place)
	 */
	@Test
	public void testAddCoupon() throws CouponException {
		assertTrue(dao.getCoupon(174).getName().equals("firstCoupon"));
	}

	/**
	 * adding a new coupon and getting the size of the coupons in the DB, and then deleting this coupon from DB and
	 * getting the new size
	 */
	@Test
	public void testDeleteCoupon() throws CouponException {
		Coupon anotherCoupon = new Coupon();
		anotherCoupon = new Coupon();
		anotherCoupon.setId(274);
		anotherCoupon.setName("anotherCoupon");
		anotherCoupon.setDescription("anotherCoupon");
		anotherCoupon.setCategory("anotherCoupon");
		anotherCoupon.setValue(200);
		anotherCoupon.setPercentDiscount(70);
		anotherCoupon.setExpirationDate(new Timestamp(new Date().getTime()));
		anotherCoupon.setPicURL("http://anotherCoupon");
		anotherCoupon.setLongitude(14);
		anotherCoupon.setLatitude(14);
		dao.addCoupon(anotherCoupon);
		int sizeWithAnotherCoupon = dao.getCoupons().size();
		dao.deleteCoupon(274);
		int sizeWithoutAnotherCoupon = dao.getCoupons().size();
		assertTrue(sizeWithAnotherCoupon == 22 && sizeWithoutAnotherCoupon == 21);
	}

	/** try to update the coupon we added in the setUp method */
	@Test
	public void testUpdateCoupon() throws CouponException {
		couponToAdd.setCategory("anotherCategory");
		dao.updateCoupon(couponToAdd);
		retrievedCoupon = dao.getCoupon(174);
		assertTrue(retrievedCoupon.getCategory().equals("anotherCategory"));
	}

}
