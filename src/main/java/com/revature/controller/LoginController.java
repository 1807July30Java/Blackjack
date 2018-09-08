package com.revature.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.revature.beans.Account;
import com.revature.service.LoginService;

@Controller("loginController")
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	
	// login path
	@GetMapping(value = "/login")
	public String getStaticFlashcardPage() {
		return "forward:/static/.html";
	}
}
