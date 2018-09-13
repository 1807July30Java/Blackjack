package com.revature.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.revature.beans.Card;

@Service(value = "gameService")
public class GameService {
	
	/*
	 * This function checks the value of your hand by first checking the value of
	 * all non-ace cards. It then checks the value of ace cards when compared to the
	 * current total value.
	 * 
	 * This solves the problem of staggered aces and cards with a value of 7-9.
	 * 
	 * This method is meant to be called every time the hand changes.
	 */

	public int checkHandTotal(List<Card> c) {
		int total = 0; // total cards value (hand, an be used for decks, but it shouldn't :/ )
		int aceNum = 0;
		int aceTotal = 0; // tracks value of aces
		int numTotal = 0;
		for (int i = 0; i < c.size(); i++) {// go over all cards in hand
			if (c.get(i).getVal() != 1) {// if this card is NOT an Ace
				numTotal = numTotal + c.get(i).getValue(true); // add value to numTotal
			} else { // if this card is an Ace
				aceNum++;
			}
		}
		for (int i = 1; i <= aceNum; i++) { // for all our aces
			if (((numTotal + aceTotal + (aceNum - 1)) > 21) || ((numTotal + 11 + (aceNum - 1)) > 21)) { // if this AND
																										// the other (if any)
																										// aces will
																										// make us bust
				aceTotal = aceTotal + 1; // add value of 1
			} else {
				aceTotal = aceTotal + 11; // add value of 11
			}

		}

		total = numTotal + aceTotal; // assign combined value to total
		return total; // total value of my hand
	}

	public boolean dealerDraws(List<Card> c) {// boolean to check if the dealer draws
		if (checkHandTotal(c) < 16) { // if hand is less than 16
			return true; // yeah
		}
		return false; // nah
	}

}
