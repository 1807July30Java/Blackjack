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
@Table(name="DB_CARD")
public class Card {

	enum Suit {
		H, D, C, S // heart,diamond,club,spade
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="cardSequence")
	@SequenceGenerator(allocationSize=1,name="cardSequence",sequenceName="SQ_CARD_ID_PK")
	@Column(name="CARD_ID")
	private int id;
	@Column(name="SUIT")
	private Suit suit;
	@Column(name="VAL")
	private int val;
	@ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="PLAYER_ID")
	private Player playerHand;
	@ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="DECK_ID")
	private Deck deck;
	
	
	public Card(String suit, int val) {
		super();
		this.suit = Suit.valueOf(suit);
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
	 * @param aceEleven - Is ace considered with value of 11
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

	public Suit getSuit() {
		return this.suit;
	}

	public String suitString() {
		switch (this.suit) {
		case H:
			return "Hearts";
		case D:
			return "Diamonds";
		case C:
			return "Clubs";
		case S:
			return "Spades";
		default:
			return "???";
		}

	}

	public void setSuit(Suit suit) {
		this.suit = suit;
	}

	@Override
	public String toString() {
		return "Card [suit=" + this.suitString() + ", val=" + val + "]";
	}

}
