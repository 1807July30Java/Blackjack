package com.revature.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.revature.beans.Account;
import com.revature.beans.User;
import com.revature.service.AccountService;

@Controller("accountController")
public class AccountController {

	@Autowired
	private AccountService accountService;
	
	
	// login path
	@GetMapping(value = "/login")
	public String getStaticFlashcardPage() {
		return "forward:/static/.html";
	}
	
	@PostMapping("/addAccount")
	@ResponseBody
	public ResponseEntity<String> addFlashcard(@RequestBody Account account) {

		ResponseEntity<String> resp = null;
		try {
			accountService.addAccount(account);
			resp = new ResponseEntity<>("ACCOUNT CREATED SUCCESSFULLY", HttpStatus.OK);
		} catch (Exception e) {
			resp = new ResponseEntity<>("FAILED TO CREATE ACCOUNT", HttpStatus.BAD_REQUEST);
		}
		return resp;
	}
	
	@RequestMapping(value = "/addAccountTest", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<String> addAccountTest() {
		ResponseEntity<String> resp = null;
		try {
			accountService.addAccount(new Account("Grandmaster","tep"));
			resp = new ResponseEntity<>("ACCOUNT CREATED SUCCESSFULLY", HttpStatus.OK);
		} catch (Exception e) {
			resp = new ResponseEntity<>("FAILED TO CREATE ACCOUNT", HttpStatus.BAD_REQUEST);
		}
		return resp;
	}
	
	@GetMapping("/all")
    @ResponseBody
    public ResponseEntity<List<Account>> getAllAccounts() {
		System.out.println(accountService.getAllAccounts());
        return new ResponseEntity<>(accountService.getAllAccounts(), HttpStatus.OK);
    }
}
