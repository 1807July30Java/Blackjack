package com.revature.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.revature.beans.Player;
import com.revature.util.HibernateUtil;

public class PlayerDaoImpl implements PlayerDao {

	private SessionFactory sf = HibernateUtil.getSessionFactory();
	
	@Override
	public Player getAccountById(int id) {
		Session s = sf.openSession();
		Player p = null;
		
		try {
			p = (Player) s.get(Player.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		s.close();
		return p;
	}

	@Override
	public int addAccount(Player p) {
		Session s = sf.openSession();
		int id = 0;
		Transaction tx = s.beginTransaction();
		try {
			id = (int) s.save(p);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}

		s.close();
		
		return id;
	}

	@Override
	public void updateAccount(Player p) {
		Session s = sf.openSession();
		Transaction tx = s.beginTransaction();
		try {
			s.merge(p);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}

		s.close();
	}

	@Override
	public void deleteAccount(Player p) {
		Session s = sf.openSession();
		Transaction tx = s.beginTransaction();
		try {
			s.delete(p);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		}

		s.close();
	}

}
