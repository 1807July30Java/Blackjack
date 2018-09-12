package com.revature.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.beans.Card;
import com.revature.beans.Room;
import com.revature.repository.RoomRepository;

@Service(value = "roomService")
public class RoomService {

	@Autowired
	RoomRepository rr;

	private final String[] suits = { "H", "D", "C", "S" };
	private final int[] values = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13 };

	public void findRoom() {
		// check if available room
		Room r = rr.getOpenRoom();
		if (r != null) {
			// room exists
		} else {
			// create room
			int roomId = rr.save(new Room(4, "open"));
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
		}
	}
	
	public List<Room> getAllRooms() {
		return rr.getRooms();
	}
	
	public List<Card> getAllCards() {
		return rr.getAllCards();
	}
}
