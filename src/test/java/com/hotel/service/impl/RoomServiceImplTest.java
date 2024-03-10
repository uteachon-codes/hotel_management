package com.hotel.service.impl;

import com.hotel.model.Room;
import com.hotel.repository.RoomRepository;
import com.hotel.service.RoomService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
class RoomServiceImplTest {

    @Mock
    private RoomRepository roomRepository;
    private RoomService roomService;
    Room room;
    AutoCloseable autoCloseable;

    @BeforeEach
    void setUp() {
        Map amenities = new HashMap<String,Boolean>();
        amenities.put("Microwave",true);
        room = new Room();
        room.setId(10);
        room.setRoomNumber("101D");
        room.setRoomType("QND2");
        room.setStatus("dirty");
        room.setMaxOccupancy(150);
        room.setAmenities(amenities);
        room.setCreateDate(new Date());
        room.setUpdateDate(new Date());
        autoCloseable = MockitoAnnotations.openMocks(this);
        roomService = new RoomServiceImpl(roomRepository);

    }


    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void testCreateRoom() {
        mock(Room.class);
        mock(RoomRepository.class);

        when(roomRepository.save(room)).thenReturn(room);
        assertThat(roomService.createRoom(room).getRoomNumber()).isEqualTo(room.getRoomNumber());
    }

    @Test
    void testGetRoombyId() {

        mock(Room.class);
        mock(RoomRepository.class);

        when(roomRepository.findById(10)).thenReturn(Optional.ofNullable(room));

        assertThat(roomService.getRoombyId(10)).isEqualTo(room);
    }

    @Test
    void testGetAllRoom() {
        mock(Room.class);
        mock(RoomRepository.class);

        when(roomRepository.findAll()).thenReturn(new ArrayList<>(Collections.singleton(room)));

        assertThat(roomService.getAllRoom().get(0).getRoomNumber()).isEqualTo(room.getRoomNumber());
    }

    @Test
    void testUpdateRoomByFields() {

        mock(Room.class);
        mock(RoomRepository.class);

        when(roomRepository.findById(10)).thenReturn(Optional.ofNullable(room));
        when(roomRepository.save(room)).thenReturn(room);

        assertThat(roomService.updateRoomByFields(room.getId(),room)).isEqualTo(room);

    }
}