package com.hotel.controller;

import com.hotel.model.Room;
import com.hotel.repository.RoomRepository;
import com.hotel.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/room")
public class RoomInfoController {

    private RoomService roomService;

    @Autowired
    private RoomRepository roomRepository;


    public RoomInfoController(RoomService roomService) {
        super();
        this.roomService = roomService;
    }

//	createRoom() method/endpoint handles a POST request to create a Room by accepting a Room object in the request body,
//	calling the createRoom method of roomService, 
//	and returning the created Room object in the response with an HTTP status code of 200 (OK).

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public ResponseEntity<Room> createRoom(@RequestBody Room room) {
        return new ResponseEntity<Room>(roomService.createRoom(room), HttpStatus.OK);
    }

    //	getRoomById() method/endpoint handles a GET request to get a Room by its id using Service layer
    @RequestMapping(path = "/get/{id}", method = RequestMethod.GET)
    public ResponseEntity<Room> getRoomById(@PathVariable int id) {

        return new ResponseEntity<Room>(roomService.getRoombyId(id), HttpStatus.OK);
    }

    //	getAllRooms() method/endpoint handles a GET request to get All the rooms using methods in servcie layer
    @RequestMapping(path = "/get", method = RequestMethod.GET)
    public List<Room> getAllRooms() {
        List<Room> roomList = roomService.getAllRoom();
        return roomList;
    }

    // updateRoom() method handles the patch request and is used to update the room
    // details using the service method updateRoom


    @RequestMapping(path = "/update/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<Room> updateRoom(@PathVariable int id, @RequestBody Map<String, Object> fields) {
        return new ResponseEntity<Room>(roomService.updateRoomByFields(id, fields), HttpStatus.OK);
    }
}
