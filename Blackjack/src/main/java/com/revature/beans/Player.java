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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	
	public Player(User user, Room gameRoom) {
		super();
		this.user = user;
		this.gameRoom = gameRoom;
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

	public Player(int id, List<Card> playerHand, User user) {
		super();
		this.id = id;
		this.playerHand = playerHand;
		this.user = user;
	}

	public Player(int id, List<Card> playerHand, User user, Room gameRoom) {
		super();
		this.id = id;
		this.playerHand = playerHand;
		this.user = user;
		this.gameRoom = gameRoom;
	}

	public Player(List<Card> playerHand, User user, Room gameRoom) {
		super();
		this.playerHand = playerHand;
		this.user = user;
		this.gameRoom = gameRoom;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="playerIdSequence")
	@SequenceGenerator(allocationSize=1,name="playerIdSequence",sequenceName="SQ_PLAYER_ID_PK")
	@Column(name="PLAYER_ID")
	private int id;
	
	@OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	List<Card> playerHand;

	@OneToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	private User user;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "ROOM_ID")
	private Room gameRoom;
	
	@Column(name="IS_DEALER")
	private int isDealer = 0;

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Room getGameRoom() {
		return gameRoom;
	}

	public void setGameRoom(Room gameRoom) {
		this.gameRoom = gameRoom;
	}

	@Override
	public String toString() {
		return "Player [id=" + id + ", playerHand=" + playerHand + ", user=" + user + ", gameRoom=" + gameRoom
				+ ", isDealer=" + isDealer + "]";
	}
	
}