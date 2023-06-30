package com.hotel.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hotel.model.Room;
import com.hotel.repository.RoomRepository;
import com.hotel.service.RoomService;

@ExtendWith(MockitoExtension.class)
class RoomServiceImplTest {

	@Mock
	private RoomRepository roomRepository;

	@InjectMocks
	private RoomServiceImpl roomService;

	@Test
	void getAllRoom() {
		roomService.getAllRoom();
		verify(roomRepository).findAll();
	}

	@Test
	void getRoombyId() {

		// Arrange
		int roomId = 1;

		// Create a dummy room object for testing
		Room dummyRoom = new Room();

		Optional<Room> optionalRoom = Optional.of(dummyRoom);

		// Define the behavior of the mock object
		when(roomRepository.findById(roomId)).thenReturn(optionalRoom);

		// Act
		roomService.getRoombyId(1);

		// Assert
		// Verify that the findById() method of the mock object is called with the
		// expected room ID
		verify(roomRepository).findById(1);
	}

	@Test
	void createRoom() {

		Room room = new Room();

		LocalDate currentDate = LocalDate.of(2023, 6, 30);
		java.sql.Date sqlDate = java.sql.Date.valueOf(currentDate);

		room.setCreateDate(sqlDate);
		room.setId(1);

		// Mock the save() method of the roomRepository
		when(roomRepository.save(room)).thenReturn(room);

		// Act
		Room createRoom = roomService.createRoom(room);

		// Assert
		verify(roomRepository).save(room);
	}
}
