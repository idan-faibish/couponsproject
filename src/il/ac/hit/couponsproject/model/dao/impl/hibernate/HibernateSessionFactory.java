package il.ac.hit.couponsproject.model.dao.impl.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 * This is a singleton class for retrieving the HibernateSessionFactory object.<br/>
 * and through this object we can get a one and only SessionFactory object for working in this project.
 * 
 * @author Idan Faibish
 * @author Shimi Karaso
 *
 */
public class HibernateSessionFactory {
	
	private static HibernateSessionFactory hibernateSessionFactory = null;
	private static SessionFactory sessionFactory = null;
	
	/* prevents the users from directly instantiate objects of this class */
	private HibernateSessionFactory() {
		sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
	}

	/**
	 * Invoking this method will provide the the only object of HibernateSessionFactory class
	 * 
	 * @return the only object of HibernateSessionFactory (because this class is a singleton)
	 */
	public static HibernateSessionFactory getInstance() {
		if (hibernateSessionFactory == null) {
			hibernateSessionFactory = new HibernateSessionFactory();
		}
		return hibernateSessionFactory;
	}

	
	/**
	 * @return SessionFactory object that related to the HibernateSessionFactory singleton object
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
