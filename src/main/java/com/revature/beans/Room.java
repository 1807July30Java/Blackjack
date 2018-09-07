package com.revature.beans;

import javax.persistence.Entity;

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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ROOM")
public class Room implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4349272843711618060L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roomSequence")
	@SequenceGenerator(allocationSize = 1, name = "roomSequence", sequenceName = "SQ_ROOM_PQ")
	@Column(name = "ROOM_ID")
	private int id;
	
	@Column(name = "MAX_PLAYERS")
	private int maxPlayers;
	
	@Column(name = "CURRENT_STATE")
	private String currentState;
	
	@Column(name = "CURRENT_PLAYERS")
	private List<Player> playersInRoom;
	
	//@Column(name = "")
	
	
	
	
	
	
	
	
	
	
	
	
	
}
