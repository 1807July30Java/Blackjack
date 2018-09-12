package com.revature.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.revature.beans.Account;
import com.revature.beans.User;
import com.revature.service.AccountService;

@Controller("accountController")
public class AccountController {

	@Autowired
	private AccountService accountService;

	// login path
	@GetMapping(value = "/")
	public String getStaticFlashcardPage() {
		return "forward:static/index.html";
	}

	@RequestMapping(value = "/addAccountWithForm", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public String handleAccountFormRequest(@RequestBody MultiValueMap<String, String> formParams) {
		System.out.println("form params received " + formParams);
		Account a = new Account(formParams.getFirst("username"), formParams.getFirst("password"));
		accountService.addAccount(a);
		return "forward:/all";

	}

	@RequestMapping(value = "/checkAuthentication", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@ResponseBody
	public ResponseEntity<User> handleAccountAutheticationFormRequest(@RequestBody MultiValueMap<String, String> formParams) {
		System.out.println("form params received " + formParams);
		Account a = new Account(formParams.getFirst("username"), formParams.getFirst("password"));
		
		ResponseEntity<User> resp = null;
		
			if (accountService.authentication(a)){
				User u = accountService.getUserByAccount(a);
				resp = new ResponseEntity<>(u, HttpStatus.OK);
				
			}
		return resp;

	}

	@RequestMapping(value = "/addAccountTest", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<String> addAccountTest() {
		ResponseEntity<String> resp = null;
		try {
			accountService.addAccount(new Account("Grandmaster", "test3"));
			resp = new ResponseEntity<>("ACCOUNT CREATED SUCCESSFULLY", HttpStatus.OK);
		} catch (Exception e) {
			resp = new ResponseEntity<>("FAILED TO CREATE ACCOUNT", HttpStatus.BAD_REQUEST);
			e.printStackTrace();
		}
		return resp;
	}

	@GetMapping("/all")
	@ResponseBody
	public ResponseEntity<List<Account>> getAllAccounts() {
		return new ResponseEntity<>(accountService.getAllAccounts(), HttpStatus.OK);
	}
}
