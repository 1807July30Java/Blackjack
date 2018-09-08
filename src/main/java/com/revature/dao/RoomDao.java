package com.revature.dao;

import java.util.List;

import com.revature.beans.Room;

public interface RoomDao {
	
	public List<Room> getAllRooms();

	public Room getRoomById(int id);

	public int addAccount(Room r);

	public void updateAccount(Room r);

	public void deleteAccount(Room r);
	
	
}
