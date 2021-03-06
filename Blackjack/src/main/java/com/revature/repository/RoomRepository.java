package com.revature.repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.revature.beans.Card;
import com.revature.beans.Player;
import com.revature.beans.Room;
import com.revature.beans.User;

@Repository(value = "roomRepository")
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
		if (!q.list().isEmpty()) {
			return (Room) q.list().get(0);
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

	public List<Card> getAllCards() {
		List<Card> theRoomDeck;
		Session s = sessionFactory.getCurrentSession();

		Query q = s.createQuery("select c.id, c.suit, c.val from Card c where c.room.id = 1");
		theRoomDeck = q.list();

		return theRoomDeck;

	}

	public List<Player> getPlayers() {
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

	public List<Card> dealCards(Player p) {
		Session s = sessionFactory.getCurrentSession();
		System.out.println(p.getId());
		p = (Player) s.get(Player.class, p.getId());

		System.out.println("Before:");
		System.out.println(p.getPlayerHand());
		List<Card> playerHand = new ArrayList<Card>();
		try {
			playerHand = p.getPlayerHand();
		} catch (Exception e) {
			e.printStackTrace();
		}

		int roomId = p.getGameRoom().getId();

		if (playerHand == null) {
			playerHand = new ArrayList<Card>();
		}

		Query q = s.createQuery("from Card c where c.room.id = :roomIdVar and c.player = null");
		q.setParameter("roomIdVar", roomId);
		Card c1 = (Card) q.list().get(0);
		Card c2 = (Card) q.list().get(1);

		c1.setPlayer(p);
		c2.setPlayer(p);

		playerHand.add(c1);
		playerHand.add(c2);

		p.setPlayerHand(playerHand);

		// System.out.println(c1);
		// System.out.println(c2);

		// System.out.print(p);
		s.update(p);

		List<Card> playerHand2 = new ArrayList<Card>();
		playerHand2.add(new Card(c1.getId(), c1.getSuit(), c1.getVal()));
		playerHand2.add(new Card(c2.getId(), c2.getSuit(), c2.getVal()));

		
		System.out.println(p.getPlayerHand());

		return playerHand2;

	}

	public Card getHitCard(Player p) {
		Session s = sessionFactory.getCurrentSession();
		p = (Player) s.get(Player.class, p.getId());

		List<Card> playerHand = new ArrayList<Card>();
		try {
			playerHand = p.getPlayerHand();
		} catch (Exception e) {
			e.printStackTrace();
		}

		int roomId = p.getGameRoom().getId();

		Query q = s.createQuery("from Card c where c.room.id = :roomIdVar and c.player = null");
		q.setParameter("roomIdVar", roomId);
		Card c1 = (Card) q.list().get(0);
		c1.setPlayer(p);

		playerHand.add(c1);
		p.setPlayerHand(playerHand);
		return new Card(c1.getId(), c1.getSuit(), c1.getVal());
	}

	public int getHandScore(List<Card> dealerHand) {

		int score = 0;

		int aceCount = 0;
		int acePointCount = 0;
		for (Card card : dealerHand) {
			if (card.getVal() == 1) {
				aceCount++;
			}else {
				score += card.getValue(false);
			}
		}
		
		for (int i = 0; i < aceCount; i++) { //for all our aces
			if(score + 11 <= 21) { //if we're not busting
				score += 11; //add full ace value
				acePointCount++; //and increase acePointCount
			}else if(acePointCount > 0 && score + 1 > 21) { //if acePointCount is at least 1 AND the score + 1 is greater than 21
				score -= 10;
			}else {
				score ++;
			}

		}

		return score;
	}

	public List<Card> updateDealerHand(Player p) {
		Session s = sessionFactory.getCurrentSession();
		p = (Player) s.get(Player.class, p.getId());

		List<Card> playerHand = new ArrayList<Card>();
		try {
			playerHand = p.getPlayerHand();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// only G-D knows how this works
		Set<Card> hs = new HashSet<>();
		hs.addAll(playerHand);
		playerHand.clear();
		playerHand.addAll(hs);

		int roomId = p.getGameRoom().getId();

		Query q = s.createQuery("from Card c where c.room.id = :roomIdVar and c.player = null");
		q.setParameter("roomIdVar", roomId);

		// check current hand (2 cards)

		System.out.println("Pgetplayerhand" + p.getPlayerHand());
		System.out.println(playerHand);
		
		int score = getHandScore(playerHand);


		List<Card> returnHand = new ArrayList<Card>();
		// get cards until you hit score>=16
		int counterCard = 0;
		System.out.println("Score: " + score);
		while (score <= 16) {
			Card c = (Card) q.list().get(counterCard);
			c.setPlayer(p);

			returnHand.add(new Card(c.getId(),c.getSuit(),c.getVal()));
			playerHand.add(c);
			p.setPlayerHand(playerHand);

			score = getHandScore(playerHand);
			counterCard++;
		}
		s.update(p);
		return returnHand;
	}

	public Integer getHandValue(Player p) {
		Session s = sessionFactory.getCurrentSession();
		p = (Player) s.get(Player.class, p.getId());
		
		List<Card> playerHand = new ArrayList<Card>();
		try {
			playerHand = p.getPlayerHand();
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		Set<Card> hs = new HashSet<>();
		hs.addAll(playerHand);
		playerHand.clear();
		playerHand.addAll(hs);
		
		
		return getHandScore(playerHand);
	}
	
	public String deleteRoom(Room r) {
		Session s = sessionFactory.getCurrentSession();
		r = (Room) s.get(Room.class, r.getId());
		
		for(Player playerInRoom : r.getPlayersInRoom()) {
			s.delete(playerInRoom);
		}
		
		s.delete(r);
		
		return "You win!";
	}

	public Integer setWinner(Player p) {
		Session s = sessionFactory.getCurrentSession();
		p = (Player) s.get(Player.class, p.getId());
		
		Query q = s.createQuery("from Room where id = :roomIdVar");
		q.setParameter("roomIdVar", p.getGameRoom().getId());
		Room r = (Room) q.list().get(0);
		
		s.delete(r);
			
		return 1;
		
	}

}
