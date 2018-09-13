package com.revature.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.beans.Account;
import com.revature.beans.Card;
import com.revature.beans.Player;
import com.revature.beans.Room;
import com.revature.beans.User;
import com.revature.repository.RoomRepository;

@Service(value = "roomService")
public class RoomService {

	@Autowired
	RoomRepository rr;

	private final String[] suits = { "H", "D", "C", "S" };
	private final int[] values = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13 };

	public Room findRoom() {
		// check if available room
		Room r = rr.getOpenRoom();
		if (r != null) {
			return r;
		} else {
			// create room
			// currently set it to 1 player and thus it is closed
			int roomId = rr.save(new Room(1,"close"));
			Room newRoom = rr.getRoomById(roomId);
			// create set of cards
			List<Card> deck = new ArrayList<Card>();
			for (String suit : suits) {
				for (int value : values) {
					Card card = new Card(suit, value);
					card.setRoom(newRoom);
					deck.add(card);
				}
			}
			Collections.shuffle(deck);
			for(Card c :deck) {
				rr.saveCard(c);
			}
			return newRoom;
		}
	}
	
	public Player joinRoom(User u) {
		
		Room r = findRoom();
		
		Player p = new Player(u, r);
		
		rr.savePlayerToRoom(p);
		return p;
		
	}
	
	public List<Player>getAllPlayers(){
		return rr.getPlayers();
	}
	
	public List<Room> getAllRooms() {
		return rr.getRooms();
	}
	
	public List<Card> getAllCards() {
		return rr.getAllCards();
	}

	public List<Card> dealCards(Player p) {
		
		return rr.dealCards(p);
		
	}

}
