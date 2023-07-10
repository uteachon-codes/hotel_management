package com.hotel.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hotel.model.Room;

@SpringBootTest
class RoomRepositoryTest {

	@Autowired
	private RoomRepository roomRepository;
	
	@Test
	void test() {
		
		
		Optional<Room> optRoom = roomRepository.findById(1);
		Room room = optRoom.get();
		
		int actualRoomId = room.getId();
		
		assertThat(actualRoomId).isEqualTo(1);
	}

}
