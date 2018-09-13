package com.revature.beans;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The Card class is used to represent a card in a standard set of decks
 * 
 * @author Alan
 *
 */
@Entity
@Table(name = "DB_CARD")
public class Card {

	// enum Suit {
		// H, D, C, S // heart,diamond,club,spade
		// }
	
	public Card() {
		super();
	}

	public Card(int id, String suit, int val, Player player, Room room) {
		super();
		this.id = id;
		this.suit = suit;
		this.val = val;
		this.player = player;
		this.room = room;
	}

	public Card(int id, String suit, int val) {
		super();
		this.id = id;
		this.suit = suit;
		this.val = val;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cardSequence")
	@SequenceGenerator(allocationSize = 1, name = "cardSequence", sequenceName = "SQ_CARD_ID_PK")
	@Column(name = "CARD_ID")
	private int id;

	@Column(name = "SUIT")
	private String suit;

	@Column(name = "VAL")
	private int val;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "PLAYER_ID")
	private Player player;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ROOM_ID")
	private Room room;

	public Card(String suit, int val) {
		super();
		this.suit = suit;
		this.val = val;
	}

	public void setVal(int val) {
		this.val = val;
	}

	public int getVal() {
		return val;
	}

	/**
	 * Method to return the "Value" of a card in terms of the game of blackjack.
	 * 
	 * @param aceEleven
	 *            - Is ace considered with value of 11
	 * @return - the value of the card in the game of blackjack
	 */
	public int getValue(boolean aceEleven) {
		if (val == 1 && aceEleven) {
			return 11;
		} else if (val > 10) {
			return 10;
		} else {
			return val;
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public String getSuit() {
		return suit;
	}

	public void setSuit(String suit) {
		this.suit = suit;
	}

	@Override
	public String toString() {
		return "Card [id=" + id + ", suit=" + suit + ", val=" + val + ", room=" + room
				+ "]";
	}

}
