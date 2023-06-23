package com.hotel.service;

import java.util.List;

import com.hotel.model.Room;

public interface RoomService {

	public Room createRoom(Room room);
	public Room getRoombyId(int id);
	public List<Room> getAllRoom();
}
