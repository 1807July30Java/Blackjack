package com.revature.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.revature.beans.Room;
import com.revature.service.RoomService;

@Controller("roomController")
@RequestMapping("/play")
public class RoomController {

	@Autowired
	private RoomService roomService;
	
	@GetMapping(value = "/room")
	public String getStaticFlashcardPage() {
		
		// find a room for the player
		roomService.findRoom();
		
		return "forward:/static/room.html";
	}
	
	
	@GetMapping("/all")
	@ResponseBody
	public ResponseEntity<List<Room>> getAllRoom() {
		System.out.println(roomService.getAllRooms());
		return new ResponseEntity<>(roomService.getAllRooms(), HttpStatus.OK);
	}
}
