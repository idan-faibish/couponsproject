package il.ac.hit.couponsproject.model.dao.impl.hibernate;

import il.ac.hit.couponsproject.configuration.constants.IConstants;
import il.ac.hit.couponsproject.model.dao.logic.IUserDAO;
import il.ac.hit.couponsproject.model.dto.User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 * This DAO is a singleton implementation of the IUserDAO interface.<br/>
 * This implementation provides a convenient way of working with users systems,<br/>
 * and it uses Hibernate (and MySQL) as the underlying DB for storing the data.
 * 
 * @author Idan Faibish
 * @author Shimi Karaso
 *
 */
public class HibernateUserDAO implements IUserDAO {

	private static MessageDigest digester;
	private SessionFactory factory = null;
	private static HibernateUserDAO dao = null;

	static {
		try {
			digester = MessageDigest.getInstance(IConstants.MD5);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	/*
	 * prevents the users from directly instantiate objects of this class
	 */
	private HibernateUserDAO() {
		factory = new AnnotationConfiguration().configure().buildSessionFactory();
	}

	/**
	 * Invoking this method will provide the the only object of HibernateUserDAO
	 * 
	 * @return the only object of HibernateUserDAO (because this class is a singleton)
	 */
	public static HibernateUserDAO getInstance() {
		if (dao == null) {
			dao = new HibernateUserDAO();
		}
		return dao;
	}

	/**
	 * This method is getting a User object and adding him to the underlying DB
	 * 
	 * @param userToAdd
	 *            User object that you want to add to the DB
	 * @return returns <em>true</em> if the operation was finished successfully, otherwise returns <em>false<em>
	 */
	@Override
	public boolean addUser(User userToAdd) {
		Session session = null;
		Transaction transaction = null;
		try {
			String passwordInMD5 = encryptMD5(userToAdd.getPassword());
			session = factory.openSession();
			transaction = session.beginTransaction();

			User encryptedUser = new User();
			encryptedUser.setUserName(userToAdd.getUserName());
			encryptedUser.setPassword(passwordInMD5);
			session.save(encryptedUser);
			transaction.commit();
			return true;
		} catch (HibernateException e) {
			if (transaction != null)
				transaction.rollback();
			return false;
		} catch (IllegalArgumentException e) {
			return false;
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	/**
	 * This method is getting a userName string and deletes his corresponding user from the DB
	 * 
	 * @param userName
	 *            a String represents the name of the User you want to delete from the DB
	 * @return returns <em>true</em> if the operation was finished successfully, otherwise returns <em>false</em>
	 */
	@Override
	public boolean deleteUser(String userName) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = factory.openSession();
			transaction = session.beginTransaction();
			User userToDelete = (User) session.get(User.class, userName);
			session.delete(userToDelete);
			transaction.commit();
			return true;
		} catch (HibernateException e) {
			if (transaction != null)
				transaction.rollback();
			return false;
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	/**
	 * This method is getting an User object and checks if he exists in the DB
	 * 
	 * @param userToCheck
	 *            User object to check is existance in the underlying DB
	 * @return returns <em>true</em> if the user is indeed exist in the DB, otherwise returns <em>false</em>
	 */
	@Override
	public boolean isUserExist(User userToCheck) {
		Session session = null;
		try {
			session = factory.openSession();
			User user = (User) session.get(User.class, userToCheck.getUserName());
			if (user == null) {
				return false;
			}
			String encryptedPassword = encryptMD5(userToCheck.getPassword());
			if (user.getPassword().equals(encryptedPassword))
				return true;
			else {
				return false;
			}
		} catch (HibernateException | IllegalArgumentException e) {
			return false;
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
	
	/*
	 * This method is a helper one.
	 * it's all purpose is to get a String and to return his encrypted String by using MD5 algorithm.
	 */
	private String encryptMD5(String passwordToEncrpyt) throws IllegalArgumentException {
		if (passwordToEncrpyt == null || passwordToEncrpyt.length() == 0) {
			throw new IllegalArgumentException("String to encrypt cannot be null or zero length");
		}
		digester.update(passwordToEncrpyt.getBytes());
		byte[] hash = digester.digest();
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < hash.length; i++) {
			if ((0xff & hash[i]) < 0x10) {
				hexString.append("0" + Integer.toHexString((0xFF & hash[i])));
			} else {
				hexString.append(Integer.toHexString(0xFF & hash[i]));
			}
		}
		return hexString.toString();
	}

}
