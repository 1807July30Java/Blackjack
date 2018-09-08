package com.revature.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.beans.User;
import com.revature.repository.UserRepository;

@Service(value="userService")
public class UserService {

	@Autowired
	UserRepository ur;
	
	
	public User getUserById(int id) {
		return ur.getUserById(id);
	}
	
}
