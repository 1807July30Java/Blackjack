package com.revature.dao;

import com.revature.beans.Player;

public interface PlayerDao {

	public Player getAccountById(int id);

	public int addAccount(Player p);

	public void updateAccount(Player p);

	public void deleteAccount(Player p);
	
}
