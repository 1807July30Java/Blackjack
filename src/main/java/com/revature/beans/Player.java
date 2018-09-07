package com.revature.beans;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "PLAYER")
public class Player implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9046769355223163697L;

	public Player() {
		super();
	}

	public Player(int id) {
		super();
		this.id = id;
	}

	public Player(List<Card> playerHand) {
		super();
		this.playerHand = playerHand;
	}

	public Player(int id, List<Card> playerHand) {
		super();
		this.id = id;
		this.playerHand = playerHand;
	}

	public Player(int id, List<Card> playerHand, Account userAccount) {
		super();
		this.id = id;
		this.playerHand = playerHand;
		this.userAccount = userAccount;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="playerIdSequence")
	@SequenceGenerator(allocationSize=1,name="playerIdSequence",sequenceName="SQ_PLAYER_ID_PK")
	@Column(name="PLAYER_ID")
	private int id;
	
	@OneToMany(mappedBy = "player", fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	List<Card> playerHand;

	@OneToOne(mappedBy = "player", fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	private Account userAccount;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Card> getPlayerHand() {
		return playerHand;
	}

	public void setPlayerHand(List<Card> playerHand) {
		this.playerHand = playerHand;
	}

	public Account getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(Account userAccount) {
		this.userAccount = userAccount;
	}

	@Override
	public String toString() {
		return "Player [id=" + id + ", playerHand=" + playerHand + ", userAccount=" + userAccount + "]";
	}
	
}
