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
import org.springframework.web.bind.annotation.ResponseStatus;

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
	
	/*
	@GetMapping(value = "/room")
	//@PostMapping(value="/joinRoom", consumes="application/json")//added
	//@ResponseStatus(HttpStatus.OK)//added
	public String getStaticFlashcardPage() {
		User u = new User("rish", "test");
		// find a room for the player
		roomService.joinRoom(u);
		
		return "forward:/static/room.html";
	}
	*/
	
	@PostMapping(value="/joinRoom", consumes="application/json")//added
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public ResponseEntity<List<Player>> getStaticFlashcardPage(@RequestBody User u) {
		// find a room for the player
		return new ResponseEntity<>(roomService.joinRoom(u), HttpStatus.OK);
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
	
	
	@PostMapping(value="/dealPlayerCards", consumes="application/json")
	//@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	//@RequestBody Player p
	public ResponseEntity<List<Card>> updatePlayerHandAtStart(@RequestBody Player p) {
		return new ResponseEntity<>(roomService.dealCards(p), HttpStatus.OK);
	}
	
	@PostMapping(value="/dealDealerCards", consumes="application/json")
	//@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	//@RequestBody Player p
	public ResponseEntity<List<Card>> updateDealerHandAtStart(@RequestBody Player p) {
		return new ResponseEntity<>(roomService.dealCards(p), HttpStatus.OK);
	}
	
	@PostMapping(value="/stay", consumes="application/json")
	@ResponseBody
	public ResponseEntity<List<Card>> stay(@RequestBody Player p) {
		return new ResponseEntity<>(roomService.stay(p), HttpStatus.OK);
	}
	
	@PostMapping(value="/hit", consumes="application/json")
	//@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	//@RequestBody Player p
	public ResponseEntity<Card> playerHit(@RequestBody Player p) {
		return new ResponseEntity<>(roomService.hit(p), HttpStatus.OK);
	}
	
	@PostMapping(value="/handValue", consumes="application/json")
	//@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	//@RequestBody Player p
	public ResponseEntity<Integer> handValue(@RequestBody Player p) {
		return new ResponseEntity<>(roomService.getHandValue(p), HttpStatus.OK);
	}
	
	@PostMapping(value="/end", consumes="application/json")
	//@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	//@RequestBody Player p
	public ResponseEntity<String> winner(@RequestBody Player p) {
		return new ResponseEntity<>(roomService.setWinner(p), HttpStatus.OK);
	}
	
}
