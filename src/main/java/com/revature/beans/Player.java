package com.revature.beans;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PLAYER")
public class Player implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9046769355223163697L;

	@OneToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	private int userId;
	
	//@Column(name = "HAND")
	//List<Card> playerHand;
	
	//@Column(name = "TABLE_STATE")
	
	
}
