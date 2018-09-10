package com.revature.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

enum TABLE_STATE{
	WAITING, PLAYER_TURN, DEALER_TURN, PLAYER_HIT, PLAYER_STAND, GAME_DONE;
}

@Entity
@Table(name = "ROOM")
public class Room implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4349272843711618060L;
	
	public Room() {
		super();
	}

	

	public Room(int maxPlayers, String currentState, List<Card> cards) {
		super();
		this.maxPlayers = maxPlayers;
		this.currentState = currentState;
		this.cards = cards;
	}



	public Room(int maxPlayers, List<Player> playersInRoom, String currentState) {
		super();
		this.maxPlayers = maxPlayers;
		this.currentState = currentState;
		this.playersInRoom = playersInRoom;
	}

	public Room(int id, int maxPlayers, String currentState, List<Player> playersInRoom) {
		super();
		this.id = id;
		this.maxPlayers = maxPlayers;
		this.currentState = currentState;
		this.playersInRoom = playersInRoom;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roomSequence")
	@SequenceGenerator(allocationSize = 1, name = "roomSequence", sequenceName = "SQ_ROOM_PQ")
	@Column(name = "ROOM_ID")
	private int id;
	
	@Column(name = "MAX_PLAYERS")
	private int maxPlayers;
	
	@Column(name = "CURRENT_STATE")
	private String currentState;
	
	@OneToMany(mappedBy = "gameRoom", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Player> playersInRoom;
	
	@OneToMany(mappedBy = "room", fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	public List<Card> cards = new ArrayList<Card>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMaxPlayers() {
		return maxPlayers;
	}

	public void setMaxPlayers(int maxPlayers) {
		this.maxPlayers = maxPlayers;
	}

	public String getCurrentState() {
		return currentState;
	}

	public void setCurrentState(String currentState) {
		this.currentState = currentState;
	}

	public List<Player> getPlayersInRoom() {
		return playersInRoom;
	}

	public void setPlayersInRoom(List<Player> playersInRoom) {
		this.playersInRoom = playersInRoom;
	}

	@Override
	public String toString() {
		return "Room [id=" + id + ", maxPlayers=" + maxPlayers + ", currentState=" + currentState + ", playersInRoom="
				+ playersInRoom + "]";
	}
	
}
