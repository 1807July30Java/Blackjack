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
@Table(name="ACCOUNT")
public class Account implements Serializable {

	private static final long serialVersionUID = 1147410840332675471L;
	
	

	public Account(String username, String password, int isAdmin) {
		super();
		this.username = username;
		this.password = password;
		this.isAdmin = isAdmin;
	}

	public Account(int id, String username, String password, int isAdmin, User user) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.isAdmin = isAdmin;
		this.user = user;
	}

	public Account(String username, String password, int isAdmin, User user) {
		super();
		this.username = username;
		this.password = password;
		this.isAdmin = isAdmin;
		this.user = user;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="categorySequence")
	@SequenceGenerator(allocationSize=1,name="categorySequence",sequenceName="SQ_CATEGORY_PK")
	@Column(name="ACCOUNT_ID")
	private int id;
	
	@Column(name="USERNAME", nullable = false, length = 30)
	private String username;


	@Column(name="PASSWORD", nullable = false, length = 30)
	private String password;
	

	@Column(name = "ISADMIN", nullable = false)
	private int isAdmin = 0;
	
	@OneToOne(mappedBy = "account", fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	private User user;
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", username=" + username + ", password=" + password + ", isAdmin=" + isAdmin + "]";
	}
	
	
}
