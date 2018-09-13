package com.revature.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.revature.beans.Card;
import com.revature.beans.Player;
import com.revature.beans.Room;
import com.revature.beans.User;
import com.revature.service.RoomService;

@Controller("roomController")
@RequestMapping("/play")
public class RoomController {

	@Autowired
	private RoomService roomService;
	
	
	@GetMapping(value = "/room")
	//@PostMapping(value="/joinRoom", consumes="application/json")//added
	//@ResponseStatus(HttpStatus.OK)//added
	public String getStaticFlashcardPage() {
		User u = new User("rish", "test");
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
	
	
	@PostMapping(value="/dealCards", consumes="application/json")
	//@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	//@RequestBody Player p
	public ResponseEntity<List<Card>> updatePlayerHandAtStart(@RequestBody Player p) {
		//System.out.println(data);
		//System.out.println(roomService.dealCards(p));
		return new ResponseEntity<>(roomService.dealCards(p), HttpStatus.OK);
	}
	
	
	
	
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
