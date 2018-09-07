package com.revature.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.revature.beans.User;
import com.revature.util.HibernateUtil;

public class UserDaoImpl implements UserDao {
	
	private SessionFactory sf = HibernateUtil.getSessionFactory();

	@Override
	public List<User> getUsers() {
		Session s = sf.openSession();
		List<User> ul = null;
		
		try {
			ul = s.createQuery("from User").list();
		} catch (Exception e) {
			e.printStackTrace();
		}

		s.close();
		
		return ul;
	}

	@Override
	public User getUserById(int id) {
		Session s = sf.openSession();
		User u = null;
		
		try {
			u = (User) s.get(User.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		s.close();
		return u;
	}

	@Override
	public int addUser(User u) {
		Session s = sf.openSession();
		int id = 0;
		Transaction tx = s.beginTransaction();
		try {
			id = (int) s.save(u);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}

		s.close();
		
		return id;
	}

	@Override
	public void updateUser(User u) {
		Session s = sf.openSession();
		Transaction tx = s.beginTransaction();
		try {
			s.merge(u);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}

		s.close();

	}

	@Override
	public void deleteUser(User u) {
		Session s = sf.openSession();
		Transaction tx = s.beginTransaction();
		try {
			s.delete(u);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}

		s.close();

	}

}
