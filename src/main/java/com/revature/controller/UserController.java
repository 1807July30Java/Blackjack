package com.revature.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.revature.beans.User;
import com.revature.service.UserService;

@Controller("userController")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/{AccountId}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<User> getUserById(@PathVariable int AccountId) {
		ResponseEntity<User> resp = null;
		User u = userService.getUserById(AccountId);
		resp = new ResponseEntity<>(u, HttpStatus.OK);

		return resp;
	}
}
