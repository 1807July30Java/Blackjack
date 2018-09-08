package com.revature;

import static org.junit.Assert.*;

import org.junit.Test;

import com.revature.beans.Card;

public class CardTest {


	@Test
	public void aceAsOne() {
		Card aceOfHearts = new Card("H",1);
		assertEquals(aceOfHearts.getValue(false),1);
	}
	
	@Test
	public void aceAsEleven() {
		Card aceOfHearts = new Card("H",1);
		assertEquals(aceOfHearts.getValue(true),11);
	}
	
	@Test
	public void valueOfTenIsTen() {
		Card tenOfSpades = new Card("S",10);
		assertEquals(tenOfSpades.getValue(false),10);
	}
	@Test
	public void valueOfJackIsTen() {
		Card jackOfClubs = new Card("C",11);
		assertEquals(jackOfClubs.getValue(false),10);
	}
	
	@Test
	public void valueOfQueenIsTen() {
		Card queenOfDiamonds = new Card("D",12);
		assertEquals(queenOfDiamonds.getValue(false),10);
	}
	
	@Test
	public void valueOfKingIsTen() {
		Card kingOfHearts = new Card("H",13);
		assertEquals(kingOfHearts.getValue(false),10);
	}
	
}
