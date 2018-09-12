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
import com.revature.service.GameService;
import com.revature.util.HibernateUtil;

public class Driver {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in); // STUFF NEW CODE DOESN'T NEED
		Deck d = new Deck(); // STUFF NEW CODE DOESN'T NEED
		List<Card> cp = new ArrayList<Card>(); // player
		List<Card> cd = new ArrayList<Card>(); // dealer
		Player p = new Player(); // STUFF NEW CODE DOESN'T NEED
		Player dealer = new Player(); // STUFF NEW CODE DOESN'T NEED
		boolean condition = false; // STUFF NEW CODE DOESN'T NEED
		GameService gs = new GameService();

		d.shuffle(); // STUFF NEW CODE DOESN'T NEED

		cp.add(d.draw());//player cards
		cp.add(d.draw());//
		cd.add(d.draw());//dealer cards
		cd.add(d.draw());//
		dealer.setPlayerHand(cd); //put cards in hand
		p.setPlayerHand(cp); //put cards in hand

		int handTotal = 0;
		int dealHandTotal = 0;

		dealHandTotal = gs.checkHandTotal(dealer.getPlayerHand()); //starting hand total
		System.out.println(dealer.getPlayerHand().size());

		handTotal = gs.checkHandTotal(p.getPlayerHand());//starting hand total
		if (dealer.getPlayerHand().get(0).getVal() == 1) { // display dealer's first card
			System.out.println("Dealer's first card is an Ace");
		} else {
			System.out.println("Dealer's first card is worth " + dealer.getPlayerHand().get(0).getValue(false));
		}

		do {

			if (handTotal < 21) {
				System.out.println("Your hand total is " + handTotal + ".");
				System.out.println(p.getPlayerHand());
				System.out.println("Hit or Stay?");
				String us = sc.nextLine();
				if (us.equalsIgnoreCase("Hit")) {
					p.getPlayerHand().add(d.draw());
					System.out.println(p.getPlayerHand().get(p.getPlayerHand().size() - 1).getVal()); // this is the
																										// card you
																										// picked up
					System.out.println(p.getPlayerHand()); // look at your hand
					handTotal = gs.checkHandTotal(p.getPlayerHand());
					condition = true;
				} else if (us.equalsIgnoreCase("Stay") && (gs.dealerDraws(dealer.getPlayerHand())== false)) {
					if (handTotal > dealHandTotal) {
						System.out.println("Dealer's total is " + dealHandTotal);
						System.out.println("WIN!");
						System.out.println("Dealer's Cards: "+dealer.getPlayerHand());
						condition = false;
					} else if (handTotal < dealHandTotal) { // if we add more players, here is where we need to add
															// logic that asks if everyone else is done
						System.out.println("Dealer's total is " + dealHandTotal);
						System.out.println("LOSE...");
						System.out.println("Dealer's Cards: "+dealer.getPlayerHand());
						condition = false;
					} else {
						System.out.println("Dealer's total is " + dealHandTotal);
						System.out.println("DRAW :| ");
						System.out.println("Dealer's Cards: "+dealer.getPlayerHand());
						condition = false;
					}
				}

				if (gs.dealerDraws(dealer.getPlayerHand())) {
					dealer.getPlayerHand().add(d.draw());
					System.out.println("Dealer Hits");
					System.out.println(dealer.getPlayerHand().get(dealer.getPlayerHand().size() - 1).getVal());
					dealHandTotal = gs.checkHandTotal(dealer.getPlayerHand());
					condition = true;
				}

			} else if (handTotal == 21) {
				System.out.println("BLACKJACK!!");
				condition = false;
			} else {
				System.out.println("BUST.");
				System.out.println("Dealer's Cards: "+dealer.getPlayerHand());
				condition = false;
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

	}

}
