package com.revature.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.revature.beans.Account;
import com.revature.util.HibernateUtil;

public class Driver {

	public static void main(String[] args) {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		sf.openSession();
		
		
		Account a = new Account("Grandmaster", "Tep");
		Session s = sf.openSession();
		int id = 0;
		Transaction tx = s.beginTransaction();
		try {
			id = (int) s.save(a);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}

		s.close();
		
		System.out.println(id);
	}
	

}
