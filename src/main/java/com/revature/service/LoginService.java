package com.revature.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.beans.Account;
import com.revature.repository.LoginRepository;

@Service(value="loginService")
public class LoginService {

	@Autowired
	LoginRepository lr;
	
	
	public boolean authentication(Account a) {
		return lr.authentication(a);
	}
}
