package com.revature.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.revature.beans.Card;
import com.revature.beans.Player;

@Repository(value="playerRepository")
@Transactional
@EnableTransactionManagement 
public class PlayerRepository {
	
	@Autowired
	SessionFactory sessionFactory;
	
	public Player persistPlayer(Player p) {
		Session s = sessionFactory.getCurrentSession();
		s.persist(p);
		return p;
	}

	public void deletePlayer(Player p) {
		Session s = sessionFactory.getCurrentSession();
		Transaction transaction = s.beginTransaction();
		try {
		  s.delete(p);
		  transaction.commit();
		} catch (Throwable t) {
		  transaction.rollback();
		  throw t;
		}
		
	}
	/*
	public void updatePlayerHand(Card c) {
		
		Session s = sessionFactory.getCurrentSession();
		Transaction transaction = s.beginTransaction();
		
		try {
			s.merge();
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}

		
		
	}
	
	*/
	
	
	/*
	public void updatePlayerUserBalance(Player p) {
		
		Session s = sessionFactory.getCurrentSession();
		Transaction transaction = s.beginTransaction();
		
		try {
			s.merge(p);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}

		s.close();
		
		
	}
	*/
	
	
	
}
