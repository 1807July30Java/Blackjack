package com.revature.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("loginController")
public class LoginController {

	// login path
	@GetMapping(value = "/login")
	public String getStaticFlashcardPage() {
		return "forward:/static/.html";
	}
}
