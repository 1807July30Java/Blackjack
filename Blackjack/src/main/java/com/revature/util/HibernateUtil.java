package com.revature.util;

import org.hibernate.SessionFactory;
//import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

	private static SessionFactory sessionFactory;

	public static SessionFactory getSessionFactory() {
		if (HibernateUtil.sessionFactory == null) {
			Configuration c = new Configuration().configure();
			c.setProperty("hibernate.connection.username", System.getenv("BJ_USERNAME"));
			c.setProperty("hibernate.connection.password", System.getenv("BJ_PASSWORD"));
			c.setProperty("hibernate.connection.url", System.getenv("BJ_URL"));
			ServiceRegistry sr = new StandardServiceRegistryBuilder().applySettings(c.getProperties()).build();
			HibernateUtil.sessionFactory = c.buildSessionFactory(sr);
		}
		return HibernateUtil.sessionFactory;
	}
}

