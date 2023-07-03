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

	// updateRoom() method gets the new room details in the newRoom object and the
	// id of the room number to be changed. It gets the existing/old room object
	// from the db.
	// Updates the old room with the new room details.

	@Override
	public Room updateRoom(int id, Room newRoom) {

		// Setting the updated time using the sql date
		LocalDate currentDate = LocalDate.now();
		java.sql.Date sqlDate = java.sql.Date.valueOf(currentDate);

		Optional<Room> oldRoomOpt = roomRepository.findById(id);
		Room oldRoom = oldRoomOpt.get();
		oldRoom.setAmenities(newRoom.getAmenities());
		oldRoom.setMaxOccupancy(newRoom.getMaxOccupancy());
		oldRoom.setRoomNumber(newRoom.getRoomNumber());
		oldRoom.setRoomType(newRoom.getRoomType());
		oldRoom.setStatus(newRoom.getStatus());
		oldRoom.setUpdateDate(sqlDate);

		roomRepository.save(oldRoom);

		return oldRoom;
	}

}
