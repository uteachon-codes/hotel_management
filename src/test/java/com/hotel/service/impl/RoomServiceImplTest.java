package com.hotel.service.impl;

import com.hotel.exception.BusinessException;
import com.hotel.exception.SystemException;
import com.hotel.model.Room;
import com.hotel.repository.RoomRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
	void getRoombyId() throws BusinessException, SystemException {

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
		assertEquals(1, createRoom.getId());
		verify(roomRepository).save(room);
	}

	@Test
	void updateRoom() {
	      // Create a sample room object to use in the test
        Room newRoom = new Room();
        newRoom.setAmenities("TV, WiFi");
        newRoom.setMaxOccupancy(2);
        newRoom.setRoomNumber("101");
        newRoom.setRoomType("Standard");
        newRoom.setStatus("Available");

        // Create a sample old room with some initial values
        Room oldRoom = new Room();
        oldRoom.setId(1); // Assuming the ID is 1
        oldRoom.setAmenities("TV");
        oldRoom.setMaxOccupancy(1);
        oldRoom.setRoomNumber("102");
        oldRoom.setRoomType("Standard");
        oldRoom.setStatus("Occupied");
        oldRoom.setUpdateDate(Date.valueOf(LocalDate.now().minusDays(1))); // Set the update date to one day ago

        // Mock the behavior of roomRepository.findById
        when(roomRepository.findById(1)).thenReturn(Optional.of(oldRoom));

        // Call the method to update the room
        Room updatedRoom = roomService.updateRoom(1, newRoom);

        // Verify that the save method was called with the updated room object
        verify(roomRepository).save(oldRoom);

        // Verify that the old room's attributes have been updated
        assertEquals("TV, WiFi", oldRoom.getAmenities());
        assertEquals(2, oldRoom.getMaxOccupancy());
        assertEquals("101", oldRoom.getRoomNumber());
        assertEquals("Standard", oldRoom.getRoomType());
        assertEquals("Available", oldRoom.getStatus());

        // Verify that the update date is set to the current date
        assertEquals(Date.valueOf(LocalDate.now()), oldRoom.getUpdateDate());

        // Verify that the returned room is the same as the updated room
        assertEquals(oldRoom, updatedRoom);

	}
}