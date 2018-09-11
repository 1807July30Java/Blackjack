package com.revature.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.hibernate.SessionFactory;

import com.revature.beans.Account;
import com.revature.beans.Card;
import com.revature.beans.Deck;
import com.revature.beans.Player;
import com.revature.beans.User;
import com.revature.dao.AccountDao;
import com.revature.dao.AccountDaoImpl;
import com.revature.dao.UserDao;
import com.revature.dao.UserDaoImpl;
import com.revature.util.HibernateUtil;

public class Driver {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		Deck d = new Deck();
		List<Card> cp = new ArrayList<Card>(); //player
		List<Card> cd = new ArrayList<Card>(); //dealer
		Player p = new Player();
		Player dealer = new Player();
		boolean condition = false;

//		System.out.println(d.size());
//		System.out.println(d.cards.toString());
		d.shuffle(); // shuffle deck since every deck is made ordered
//		System.out.println(d.cards.toString());

//		for (int i = 0; i < 2; i++) {
//			cp.add(d.draw());
//			System.out.println(cp.get(i));
//			cd.add(d.draw());
//			System.out.println(cd.get(i));
//		}
//		
//
		
		cp.add(d.draw());
		cp.add(d.draw());
		cd.add(d.draw());
		cd.add(d.draw());
		dealer.setPlayerHand(cd);
		p.setPlayerHand(cp);
//		System.out.println(cp.toString());
//		System.out.println(p.getPlayerHand());
//		System.out.println(dealer.getPlayerHand());
//		try {
//			dealer.getPlayerHand().add(d.draw());
//		} catch (NullPointerException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println(dealer.getPlayerHand().add(d.draw()));
//		System.out.println(p.getPlayerHand().add(d.draw()));
//		System.out.println(dealer.getPlayerHand().add(d.draw()));
//		System.out.println(p.getPlayerHand().add(d.draw()));
//		dealer.getPlayerHand().add(d.draw());
//		p.getPlayerHand().add(d.draw());
//		p.getPlayerHand().add(d.draw());

		int handTotal = 0;
		int dealHandTotal = 0;
		
		
		int dt = 0;
		for (int i = 0; i < dealer.getPlayerHand().size(); i++) {
			
			if (dealer.getPlayerHand().get(i).getVal() == 1) {
				dt = dt + dealer.getPlayerHand().get(i).getValue(true);
			} else {
				dt = dt + dealer.getPlayerHand().get(i).getValue(false);
			}

			dealHandTotal = dt;
		}
		System.out.println(dealer.getPlayerHand().size());
		
		
		int ht = 0;
		for (int i = 0; i < p.getPlayerHand().size(); i++) {
			
			if (p.getPlayerHand().get(i).getVal() == 1) {
				ht = ht + p.getPlayerHand().get(i).getValue(true);
			} else {
				ht = ht + p.getPlayerHand().get(i).getValue(false);
			}
//			System.out.println("this is the ht: "+ht);
			handTotal = ht;
		}
		if (dealer.getPlayerHand().get(0).getVal() == 1) { // display dealer's first card
			System.out.println("Dealer's first card is an Ace");
//			System.out.println(dealer.getPlayerHand());
		} else {
			System.out.println("Dealer's first card is worth " + dealer.getPlayerHand().get(0).getValue(false));
//			System.out.println(dealer.getPlayerHand());
		}

		do {

			if (handTotal < 21) {
				System.out.println("Your hand total is " + handTotal + ".");
				System.out.println(p.getPlayerHand());
				System.out.println("Hit or Stay?");
				String us = sc.nextLine();
				if (us.equalsIgnoreCase("Hit")) {
					p.getPlayerHand().add(d.draw());
					System.out.println(p.getPlayerHand().get(p.getPlayerHand().size() - 1).getVal()); //this is the card you picked up
					System.out.println(dealer.getPlayerHand()); //look at your hand
					if (p.getPlayerHand().get(p.getPlayerHand().size() - 1).getVal() == 1) { //if an ace
						if ((handTotal + p.getPlayerHand().get(p.getPlayerHand().size() - 1).getValue(true)) > 21) { //if adding it as 11 would make us bust
							handTotal = handTotal + p.getPlayerHand().get(p.getPlayerHand().size() - 1).getValue(false); // we set it to 1
						} else {
							handTotal = handTotal + p.getPlayerHand().get(p.getPlayerHand().size() - 1).getValue(true);
						}
						
					} else {
						handTotal = handTotal + p.getPlayerHand().get(p.getPlayerHand().size() - 1).getValue(false);
					}
					condition = true;
				} else if (us.equalsIgnoreCase("Stay")) {
					if (handTotal > dealHandTotal) {
						System.out.println("Dealer's total is "+dealHandTotal);
						System.out.println("WIN!");
						condition = false;
					} else if(handTotal < dealHandTotal){ // if we add more players, here is where we need to add logic that asks if everyone else is done
						System.out.println("Dealer's total is "+dealHandTotal);
						System.out.println("LOSE...");
						condition = false;
					} else {
						System.out.println("Dealer's total is "+dealHandTotal);
						System.out.println("DRAW :| ");
						condition = false;
					}
				}
				
				

			} else if (handTotal == 21) {
				System.out.println("BLACKJACK!!");
				condition = false;
			} else {
				System.out.println("BUST.");
				condition = false;
			}
			
			if(dealHandTotal < 16) {
				dealer.getPlayerHand().add(d.draw());
				System.out.println("Dealer Hits");
				System.out.println(dealer.getPlayerHand().get(dealer.getPlayerHand().size() - 1).getVal());
				if (dealer.getPlayerHand().get(dealer.getPlayerHand().size() - 1).getVal() == 1) {
					if ((dealHandTotal + dealer.getPlayerHand().get(dealer.getPlayerHand().size() - 1).getValue(true)) > 21) {
						dealHandTotal = dealHandTotal + dealer.getPlayerHand().get(dealer.getPlayerHand().size() - 1).getValue(false);
					} else {
						dealHandTotal = dealHandTotal + dealer.getPlayerHand().get(dealer.getPlayerHand().size() - 1).getValue(true);
					}
					
				} else {
					dealHandTotal = dealHandTotal + dealer.getPlayerHand().get(dealer.getPlayerHand().size() - 1).getValue(false);
				}
			}

		} while (condition);
		/*
		 * SessionFactory sf = HibernateUtil.getSessionFactory(); sf.openSession();
		 * 
		 * 
		 * // AccountDao a = new AccountDaoImpl(); //
		 * System.out.println(a.getAccounts()); Account a = new Account("Grandmaster",
		 * "Tep"); User u = new User("firstname","lastname", a); System.out.println(u);
		 * UserDao ud = new UserDaoImpl(); ud.addUser(u);
		 */

//		System.out.println(d.cards.toString());
//		System.out.println(p.getPlayerHand());
//		System.out.println(p.getPlayerHand().get(0).getValue(false));
//		System.out.println(p.getPlayerHand().get(1).getValue(false));
//		System.out.println(p.getPlayerHand().get(0).getValue(false) + p.getPlayerHand().get(1).getValue(false));
//
//		System.out.println(p.getPlayerHand().get(2).getValue(false));
//		if ((p.getPlayerHand().get(0).getValue(false) + p.getPlayerHand().get(1).getValue(false)
//				+ p.getPlayerHand().get(2).getValue(false)) > 21) {
//			System.out.println("BUST.");
//		}
//		System.out.println(p.getPlayerHand().get(0).getValue(false) + p.getPlayerHand().get(1).getValue(false)
//				+ p.getPlayerHand().get(2).getValue(false));
//		System.out.println(d.size());
//		System.out.println(d.cards.toString());
//		d.draw();
//		System.out.println(d.size());
//		System.out.println(d.cards.toString());
	}

}
