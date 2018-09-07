package com.revature;

import static org.junit.Assert.*;

import org.junit.Test;

import com.revature.beans.Card;
import com.revature.beans.Deck;

public class DeckTest {

	@Test
	public void regularDeckHasFiftyTwo() {
		Deck test = new Deck();
		assertEquals(test.size(),52);
	}
	
	@Test
	public void twoSetDeckHasOneHundredFour() {
		Deck test = new Deck(2);
		assertEquals(test.size(),104);
	}
	
	@Test
	public void firstCardOfUnshuffledDeck() {
		//will be the last card place in
		Deck test = new Deck();
		Card kingOfSpades = new Card("S",13);
		assertEquals(kingOfSpades.getVal(),test.draw().getVal());
	}
}
