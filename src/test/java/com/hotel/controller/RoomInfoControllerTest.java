package com.hotel.controller;

import com.hotel.model.Room;
import com.hotel.service.RoomService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

@WebMvcTest(RoomInfoController.class)
class RoomInfoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RoomService roomService;

    Room roomOne;
    Room roomTwo;

    List<Room>  roomList = new ArrayList<Room>();
    @BeforeEach
    void setUp() {

        Map amenities = new HashMap<String,Boolean>();
        amenities.put("Microwave",true);

        roomOne = new Room();
        roomOne.setId(10);
        roomOne.setRoomNumber("101D");
        roomOne.setRoomType("QND2");
        roomOne.setStatus("dirty");
        roomOne.setMaxOccupancy(150);
        roomOne.setAmenities(amenities);
        roomOne.setCreateDate(new Date());
        roomOne.setUpdateDate(new Date());

        roomTwo = new Room();
        roomTwo.setId(11);
        roomTwo.setRoomNumber("111D");
        roomTwo.setRoomType("KND1");
        roomTwo.setStatus("dirty");
        roomTwo.setMaxOccupancy(150);
        roomTwo.setAmenities(amenities);
        roomTwo.setCreateDate(new Date());
        roomTwo.setUpdateDate(new Date());

        roomList.add(roomOne);
        roomList.add(roomTwo);
    }

    @AfterEach
    void tearDown() {

        roomList.clear();
        roomOne = null;
        roomTwo = null;
    }

    @Test
    void createRoom() {
    }

    @Test
    void getRoomById() {
    }

    @Test
    void getAllRooms() {
    }

    @Test
    void updateRoom() {
    }
}