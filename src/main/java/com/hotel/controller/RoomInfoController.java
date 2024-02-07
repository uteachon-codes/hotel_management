package com.hotel.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hotel.model.Room;
import com.hotel.service.RoomService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for the room endpoint handling the create, update and get operations.
 *
 * @author Abdul Basith
 */


@RestController
@Validated
@RequestMapping("/room")
public class RoomInfoController {

    private RoomService roomService;

    public RoomInfoController(RoomService roomService) {
        super();
        this.roomService = roomService;
    }

    //	createRoom() method/endpoint handles a POST request to create a Room by accepting a Room object in the request body,
//	calling the createRoom method of roomService, 
//	and returning the created Room object in the response with an HTTP status code of 200 (OK).
    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public ResponseEntity<Room> createRoom(@Valid @RequestBody String roomData) throws JsonProcessingException {

        // Initialize an ObjectMapper to parse JSON data
        ObjectMapper objectMapper = new ObjectMapper();
        // Deserialize the JSON data into a Room object
        Room room = objectMapper.readValue(roomData, Room.class);
        // Call the service to create the room and get the saved room
        Room savedRoom = roomService.createRoom(room);
        // Return a ResponseEntity with the saved room and OK status
        return new ResponseEntity<Room>(savedRoom, HttpStatus.OK);
    }

    //	getRoomById() method/endpoint handles a GET request to get a Room by its id using Service layer
    @RequestMapping(path = "/get/{id}", method = RequestMethod.GET)
    public ResponseEntity<Room> getRoomById(@Valid @PathVariable int id) {
        return new ResponseEntity<Room>(roomService.getRoombyId(id), HttpStatus.OK);

    }

    //	getAllRooms() method/endpoint handles a GET request to get All the rooms using methods in servcie layer
    @RequestMapping(path = "/get", method = RequestMethod.GET)
    public ResponseEntity<List<Room>> getAllRooms() {
        List<Room> roomList = roomService.getAllRoom();
        return new ResponseEntity<>(roomList, HttpStatus.OK);
    }

    // updateRoom() method handles the patch request and is used to update the room
    // details using the service method updateRoom


    @RequestMapping(path = "/update/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<Room> updateRoom(@PathVariable int id, @RequestBody String roomData) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        Room room = objectMapper.readValue(roomData, Room.class);
        return new ResponseEntity<Room>(roomService.updateRoomByFields(id, room), HttpStatus.OK);
    }
}