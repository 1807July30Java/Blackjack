package com.revature.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.revature.beans.Account;
import com.revature.repository.AccountRepository;

@Service(value="accountService")
public class AccountService {

	@Autowired
	AccountRepository ar;
	
	
	public boolean authentication(Account a) {
		return ar.authentication(a);
	}
	
	public void addAccount(Account a) {
		ar.persistAccount(a);
	}
	
    public List<Account> getAllAccounts() {
        return ar.getAccounts();
    }
}
