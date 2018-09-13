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
import com.revature.beans.Player;
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
			return (Room)q.list().get(0);
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
		Query q = s.createQuery("select r.id, r.maxPlayers, r.currentState from Room r");
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
	
	public List<Card> getAllCards(){
		List<Card> theRoomDeck;
		Session s = sessionFactory.getCurrentSession();
		
		Query q = s.createQuery("select c.id, c.suit, c.val from Card c where c.room.id = 1");
		theRoomDeck = q.list();
		
		return theRoomDeck;
		
	}
	
	public List<Player> getPlayers(){
		List<Player> players;
		Session s = sessionFactory.getCurrentSession();
		
		Query q = s.createQuery("select p.id, p.userAccount.username from Player p where p.gameRoom.id = 4");
		players = q.list();
		
		return players;
		
	}
	
	public int savePlayerToRoom(Player p) {	
		Session s = sessionFactory.getCurrentSession();
		return (int) s.save(p);
	
	}
		/*
	public List<Card> dealCards() {
		Session s = sessionFactory.getCurrentSession();
		
		Query q 
		
		
		List<Card> theRoomDeck = getAllCards();
		List<Card> playerHand = null;
		
		
		
		
		
		return playerHand;
	}
*/
}
