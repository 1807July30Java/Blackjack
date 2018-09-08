package com.revature.beans;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;




@Entity
@Table(name="DECK")
public class Deck {
	
	//Needed for Deck Generation
	private final String[] suits = {"H","D","C","S"};
	private final int[] values = {1,2,3,4,5,6,7,8,9,10,11,12,13};
	
	//persistence
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="deckSequence")
	@SequenceGenerator(allocationSize=1,name="deckSequence",sequenceName="SQ_DECK_ID_PK")
	@Column(name="DECK_ID")
	private int id;
	@OneToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name = "ROOM_ID")
	private Room room;
	
	@OneToMany(mappedBy = "deck", fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	public List<Card> cards = new ArrayList<Card>();
	
	public Deck() {
		init();
	}

	
	public Deck(int sets) {
		init(sets);
	}
	
	private void init() {
		//Fill Deck with 1 set of 52 cards
		for(String suit : suits) {
			for(int value : values) {
				Card card = new Card(suit,value);
				cards.add(card);
			}
		}
	}
	
	private void init(int sets) {
		//Fill Deck with sets of 52 cards equal to amount of sets specified
		for(int i=0;i<sets;i++) {
			for(String suit : suits) {
				for(int value : values) {
					this.cards.add(new Card(suit,value));
				}
			}
		}
	}
	
	public void shuffle() {
		Collections.shuffle(this.cards);
	}
	
	public int size() {
		return this.cards.size();
	}
	
	public Card draw() {
		if(this.size()!=0) {
			return this.cards.remove(this.size()-1);
		} else {
			return null;
		}
	}
	
}
