package com.revature.beans;

import java.io.Serializable;

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
@Table(name="USER")
public class User implements Serializable {
	
	
	static final long serialVersionUID = 4385963673326501735L;
	
	
	public User(int id, String firstName, String lastName, int balance, Account account) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.balance = balance;
		this.account = account;
	}

	public User(String firstName, String lastName, int balance, Account account) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.balance = balance;
		this.account = account;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="categorySequence")
	@SequenceGenerator(allocationSize=1,name="categorySequence",sequenceName="SQ_CATEGORY_PK")
	@Column(name="USER_ID")
	private int id;

	@Column(name="FIRSTNAME", nullable = false, length = 30)
	private String firstName;
	
	
	@Column(name="LASTNAME", nullable = false, length = 30)
	private String lastName;
	
	@Column(name="BALANCE", nullable = false)
	private int balance = 0;
	
	@OneToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	private Account account;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", balance=" + balance
				+ ", account=" + account + "]";
	}
	
	
	
	
}
