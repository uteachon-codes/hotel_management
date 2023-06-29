package com.hotel.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.model.Room;
import com.hotel.repository.RoomRepository;
import com.hotel.service.RoomService;

@Service
public class RoomServiceImpl implements RoomService {

	private RoomRepository roomRepository;

	@Autowired
	public RoomServiceImpl(RoomRepository roomRepository) {
		super();
		this.roomRepository = roomRepository;
	}

	@Override
	public Room createRoom(Room room) {

		LocalDate currentDate = LocalDate.now();
		
		java.sql.Date sqlDate = java.sql.Date.valueOf(currentDate);
		room.setCreateDate(sqlDate);
		return roomRepository.save(room);

	}

	@Override
	public Room getRoombyId(int id) {
		Optional<Room> optionalRoom = roomRepository.findById(id);
		Room room = optionalRoom.get();
		return room;
	}

	@Override
	public List<Room> getAllRoom() {

		List<Room> rooms = roomRepository.findAll();
		return rooms;
	}

}
