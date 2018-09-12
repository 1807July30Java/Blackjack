package com.revature.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.revature.beans.Account;
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
	
	@RequestMapping(value = "/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@ResponseBody
	public ResponseEntity<String> handleAccountFormRequest(@RequestBody MultiValueMap<String, String> formParams) {
		ResponseEntity<String> resp = null;
		System.out.println("form params received " + formParams);
		
		try {
			Account a = new Account(formParams.getFirst("username"), formParams.getFirst("password"));
			int startingBalance = 1000;
			User u = new User(formParams.getFirst("firstname") , formParams.getFirst("lastname"), startingBalance, a);
			userService.addUser(u);
			
			resp = new ResponseEntity<>("ACCOUNT CREATED SUCCESSFULLY", HttpStatus.OK);
		} catch (Exception e) {
			resp = new ResponseEntity<>("FAILED TO CREATE ACCOUNT", HttpStatus.BAD_REQUEST);
			e.printStackTrace();
		}
		return resp;

	}
	
}
