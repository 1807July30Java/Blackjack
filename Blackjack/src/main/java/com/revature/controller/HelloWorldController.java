package com.revature.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("helloWorldController")
public class HelloWorldController {

	@RequestMapping(value="/hello", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<String> sayHello(){
		return new ResponseEntity<>("Hello World!",HttpStatus.OK);
	}
	

}
