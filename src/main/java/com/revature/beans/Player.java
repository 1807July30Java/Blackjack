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

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="playerIdSequence")
	@SequenceGenerator(allocationSize=1,name="playerIdSequence",sequenceName="SQ_PLAYER_ID_PK")
	@Column(name="PLAYER_ID")
	private int id;
	
	@Column(name = "HAND")
	List<Card> playerHand;
	
	
	
	
}
