package il.ac.hit.couponsproject.model.dao.impl.hibernate;

import il.ac.hit.couponsproject.configuration.constants.IConstants;
import il.ac.hit.couponsproject.model.dao.logic.ICouponDAO;
import il.ac.hit.couponsproject.model.dto.Coupon;
import il.ac.hit.couponsproject.model.exception.CouponException;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.ResourceBundle;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 * This DAO is a singleton implementation of the ICouponDAO interface.<br/>
 * This implementation provides a convenient way of working with coupons systems,<br/>
 * and it uses Hibernate (and MySQL) as the underlying DB for storing the data.
 * 
 * @author Idan Faibish
 * @author Shimi Karaso
 *
 */
public class HibernateCouponDAO implements ICouponDAO {

	private SessionFactory factory = null;
	private static HibernateCouponDAO dao = null;

	/* prevents the users from directly instantiate objects of this class */
	private HibernateCouponDAO() {
		factory = HibernateSessionFactory.getInstance().getSessionFactory();
	}

	/**
	 * Invoking this method will provide the the only object of HibernateCouponDAO
	 * 
	 * @return the only object of HibernateCouponDAO (because this class is a singleton)
	 */
	public static HibernateCouponDAO getInstance() {
		if (dao == null) {
			dao = new HibernateCouponDAO();
		}
		return dao;
	}

	/**
	 * This method will return all the coupons that are stored in the DB
	 * 
	 * @return a Collection of the coupons
	 * @throws CouponException
	 */
	@Override
	public Collection<Coupon> getCoupons() throws CouponException {
		Session session = null;
		try {
			session = factory.openSession();
			ResourceBundle resourceBundle = ResourceBundle.getBundle(IConstants.DB_BUNDLE_HIBERNATE);
			String query = resourceBundle.getString(IConstants.RES_KEY_RETRIEVE_COUPONS);

			Collection<Coupon> coupons = session.createQuery(query).list();
			return coupons;
		} catch (HibernateException e) {
			throw new CouponException("failed to get the coupons collection", e);
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	/**
	 * This method is getting an id of a specific coupon and returns his corresponding coupon from the underlying DB
	 * 
	 * @param id
	 *            represents the id of the coupon you want to get from the DB
	 * @return returns the corresponding Coupon object of the given id
	 * @throws CouponException
	 */
	@Override
	public Coupon getCoupon(int id) throws CouponException {
		Session session = null;
		try {
			session = factory.openSession();
			Coupon coupon = (Coupon) session.get(Coupon.class, id);
			if (coupon == null) {
				throw new CouponException("the coupon with id=" + id + " doesn't exist");
			}
			return coupon;
		} catch (HibernateException e) {
			throw new CouponException("failed to get coupon with id=" + id, e);
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	/**
	 * This method is getting a Coupon object and adding him to the DB
	 * 
	 * @param coupon
	 *            Coupon object that you want to add to the DB
	 * @throws CouponException
	 */
	@Override
	public void addCoupon(Coupon coupon) throws CouponException {
		Session session = null;
		Transaction transaction = null;
		try {
			session = factory.openSession();
			transaction = session.beginTransaction();
			session.save(coupon);
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw new CouponException("failed to add coupon with id=" + coupon.getId(), e);
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	/**
	 * This method is getting an id of a specific coupon and deletes his corresponding coupon from the DB
	 * 
	 * @param id
	 *            represents the id of the coupon you want to delete from the DB
	 * @throws CouponException
	 */
	@Override
	public void deleteCoupon(int id) throws CouponException {
		Session session = null;
		Transaction transaction = null;
		try {
			session = factory.openSession();
			transaction = session.beginTransaction();
			Coupon couponToDelete = (Coupon) session.get(Coupon.class, id);
			session.delete(couponToDelete);
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw new CouponException("failed to delete the coupon with id=" + id, e);
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	/**
	 * This method is getting a Coupon object and updating his contents in the DB
	 * 
	 * @param coupon
	 *            Coupon object that you want to update his contents in the DB
	 * @throws CouponException
	 */
	@Override
	public void updateCoupon(Coupon coupon) throws CouponException {
		Session session = null;
		Transaction transaction = null;
		try {
			session = factory.openSession();
			transaction = session.beginTransaction();
			session.update(coupon);
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw new CouponException("failed to update the coupon with id=" + coupon.getId(), e);
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

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
	@Override
	public Collection<Coupon> getThreeClosestCoupons(double longitude, double latitude) throws CouponException {
		Session session = null;
		try {
			session = factory.openSession();
			ResourceBundle resourceBundle = ResourceBundle.getBundle(IConstants.DB_BUNDLE_HIBERNATE);
			String query = resourceBundle.getString(IConstants.RES_KEY_GET_THREE_CLOSEST_COUPONS);

			Query queryObject = session.createQuery(query);
			queryObject.setParameter(IConstants.USER_LATITUDE, latitude);
			queryObject.setParameter(IConstants.USER_LONGITUDE, longitude);
			queryObject.setParameter(IConstants.CURRENT_DATE, new Timestamp(new Date().getTime()));
			queryObject.setMaxResults(3);
			Collection<Coupon> coupons = queryObject.list();
			if (coupons.size() != 0) {
				return coupons;
			}
			throw new CouponException("The DB is empty out of non-expired coupons");
		} catch (HibernateException e) {
			throw new CouponException("failed to get the three closeset coupons", e);
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	/**
	 * This method returns all the non-expired coupons <br/>
	 * (according to each expiration-date compared with the current date and time)
	 * 
	 * @return a Collection of all the non-expired coupons in the DB
	 * @throws CouponException
	 */
	@Override
	public Collection<Coupon> getNonExpiredCoupons() throws CouponException {
		Session session = null;
		try {
			session = factory.openSession();
			ResourceBundle resourceBundle = ResourceBundle.getBundle(IConstants.DB_BUNDLE_HIBERNATE);
			String query = resourceBundle.getString(IConstants.RES_KEY_RETRIEVE_NON_EXPIRED_COUPONS);
			Query queryObject = session.createQuery(query);

			queryObject.setParameter(IConstants.CURRENT_DATE, new Timestamp(new Date().getTime()));
			Collection<Coupon> coupons = queryObject.list();
			return coupons;
		} catch (HibernateException e) {
			throw new CouponException("failed to get non-expired coupons collection", e);
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
}
