package com.revature.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.revature.beans.Account;
import com.revature.beans.Card;
import com.revature.beans.FormData;
import com.revature.beans.Player;
import com.revature.beans.Room;
import com.revature.beans.User;
import com.revature.service.RoomService;

@Controller("roomController")
@RequestMapping("/play")
public class RoomController {

	@Autowired
	private RoomService roomService;
	
	//@GetMapping(value = "/room")
	@GetMapping(value="/joinRoom", consumes="application/json")//added
	@ResponseStatus(HttpStatus.OK)//added
	public String getStaticFlashcardPage(@RequestBody FormData data) {
		User u = new User(data.getFirstname(), data.getLastname());
		// find a room for the player
		roomService.joinRoom(u);
		
		return "forward:/static/room.html";
	}
	
	
	@GetMapping("/all")
	@ResponseBody
	public ResponseEntity<List<Room>> getAllRoom() {
		System.out.println(roomService.getAllRooms());
		return new ResponseEntity<>(roomService.getAllRooms(), HttpStatus.OK);
	}
	
	@GetMapping("/allCardsInRoom")
	@ResponseBody
	public ResponseEntity<List<Card>> getRoomDeck() {
		//System.out.println(roomService.getAllCards());
		return new ResponseEntity<>(roomService.getAllCards(), HttpStatus.OK);
	}
	
	@GetMapping("/allPlayersInRoom")
	@ResponseBody
	public ResponseEntity<List<Player>> playerJoin() {
		//System.out.println(roomService.getAllCards());
		return new ResponseEntity<>(roomService.getAllPlayers(), HttpStatus.OK);
	}
	
	/*
	@RequestMapping(value="/dealCards", method = RequestMethod.POST, consumes="application/json")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<Card>> updatePlayerHandAtStart(@RequestBody FormData data) {
		//System.out.println(data);
		Account a = new Account(data.getUsername(), data.getPassword());
		return new ResponseEntity<>(roomService.dealCards(a), HttpStatus.OK);
	}
	*/
	
	
	
	/*
	@RequestMapping(value = "/dealCardsAtStart")
	@ResponseBody
	public ResponseEntity<String> updatePlayerHandAtStart() {
		ResponseEntity<String> resp = null;
		try {
			roomService.dealCards();
			resp = new ResponseEntity<>("ACCOUNT CREATED SUCCESSFULLY", HttpStatus.OK);
		} catch (Exception e) {
			resp = new ResponseEntity<>("FAILED TO CREATE ACCOUNT", HttpStatus.BAD_REQUEST);
			e.printStackTrace();
		}
		return resp;
	}
	*/
	
}
