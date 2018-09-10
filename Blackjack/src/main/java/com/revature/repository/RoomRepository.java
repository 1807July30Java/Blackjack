package com.revature.repository;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.revature.beans.Account;
import com.revature.beans.Card;
import com.revature.beans.Room;


@Repository(value="roomRepository")
@Transactional
@EnableTransactionManagement 
public class RoomRepository {

	@Autowired
	SessionFactory sessionFactory;

	public Room getOpenRoom() {
		
		// return the first open session 
		Session s = sessionFactory.getCurrentSession();
		Query q = s.createQuery("from Room where currentState = :stateVar");
		q.setParameter("stateVar", "open");
		if(!q.list().isEmpty()) {
			return (Room)q.list().get(9);
		}
		
		return null;

	}

	public void persist(Room r) {
		Session s = sessionFactory.getCurrentSession();
		s.persist(r);
	}

	public List<Room> getRooms() {
		List<Room> rl = null;
		Session s = sessionFactory.getCurrentSession();
		Query q = s.createQuery("from Room");
		rl = q.list();
		return rl;
	}

	public int save(Room r) {
		Session s = sessionFactory.getCurrentSession();
		return (int) s.save(r);
	}

	public Room getRoomById(int id) {
		Session s = sessionFactory.getCurrentSession();
		Room r = null;
		
		r = (Room) s.get(Room.class, id);
		return r;
	}

	public int saveCard(Card c) {
		Session s = sessionFactory.getCurrentSession();
		return (int) s.save(c);
		
	}
}
