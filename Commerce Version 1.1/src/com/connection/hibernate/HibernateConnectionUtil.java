package com.connection.hibernate;



import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


public class HibernateConnectionUtil {
	//static Logger log = Logger.getLogger(HibernateConnectionUtil.class.getName());
	private static SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
	//	log.debug("**************Creating session factory");
		try {
			sessionFactory = new Configuration().configure()
					.buildSessionFactory();

			return sessionFactory;

		} catch (Throwable ex) {
			
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {

		return sessionFactory;
	}
}
