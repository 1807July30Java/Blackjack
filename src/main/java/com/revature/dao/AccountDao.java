package com.revature.dao;

import java.util.List;

import com.revature.beans.Account;

public interface AccountDao {
	
	public List<Account> getAccounts();

	public Account getAccountById(int id);

	public int addAccount(Account a);

	public void updateAccount(Account a);

	public void deleteAccount(Account a);
	
}
