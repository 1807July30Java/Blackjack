package com.revature.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.revature.beans.Account;
import com.revature.util.HibernateUtil;

public class AccountDaoImpl implements AccountDao {
	
	private SessionFactory sf = HibernateUtil.getSessionFactory();

	@Override
	public List<Account> getAccounts() {
		Session s = sf.openSession();
		List<Account> al = null;
		
		try {
			al = s.createQuery("from Account").list();
		} catch (Exception e) {
			e.printStackTrace();
		}

		s.close();
		
		return al;
	}

	@Override
	public Account getAccountById(int id) {
		Session s = sf.openSession();
		Account a = null;
		
		try {
			a = (Account) s.get(Account.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		s.close();
		return a;
	}

	@Override
	public int addAccount(Account a) {
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
		
		return id;
	}

	@Override
	public void updateAccount(Account a) {
		Session s = sf.openSession();
		Transaction tx = s.beginTransaction();
		try {
			s.merge(a);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}

		s.close();

	}

	@Override
	public void deleteAccount(Account a) {
		Session s = sf.openSession();
		Transaction tx = s.beginTransaction();
		try {
			s.delete(a);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}

		s.close();

	}

}
