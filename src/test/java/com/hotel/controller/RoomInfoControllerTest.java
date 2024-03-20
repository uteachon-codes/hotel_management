package com.hotel.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.hotel.model.Room;
import com.hotel.service.RoomService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(RoomInfoController.class)
public class RoomInfoControllerTest {

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

    @org.junit.Test(expected = NullPointerException.class)
    public void createRoom() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(roomOne);

        when(roomService.createRoom(roomOne)).thenReturn(roomOne);
        this.mockMvc.perform(post("/room/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void getRoomById() throws Exception {
        when(roomService.getRoombyId(11)).thenReturn(roomTwo);

        this.mockMvc.perform(get("/room/get/11"))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void getAllRooms() throws Exception {
        when(roomService.getAllRoom()).thenReturn(roomList);

        this.mockMvc.perform(get("/room/get"))
                .andDo(print()).andExpect(status().isOk());
    }

    @org.junit.Test(expected = NullPointerException.class)
    public void updateRoom() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(roomOne);

        when(roomService.updateRoomByFields(10,roomOne)).thenReturn(roomTwo);
        this.mockMvc.perform(patch("/room/update/10")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andDo(print())
                .andExpect(status().isOk());
    }
}