package com.revature.beans;

/**
 * The Card class is used to represent a card in a standard set of decks
 * 
 * @author Alan
 *
 */
public class Card {

	enum Suit {
		H, D, C, S // heart,diamond,club,spade
	}

	private Suit suit;
	private int val;

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
