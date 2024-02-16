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

    private final RoomService roomService;

    public RoomInfoController(RoomService roomService) {
        //We can remove the super class because it's not needed here.
        this.roomService = roomService;
    }

    //	createRoom() method/endpoint handles a POST request to create a Room by accepting a Room object in the request body,
//	calling the createRoom method of roomService, 
//	and returning the created Room object in the response with an HTTP status code of 200 (OK).
// Instead of @RequestMapping, we can use PostMapping
    @PostMapping(path = "/create")
    public ResponseEntity<Room> createRoom(@Valid @RequestBody Room room) {

            return new ResponseEntity<>(roomService.createRoom(room), HttpStatus.OK);
}

    //	getRoomById() method/endpoint handles a GET request to get a Room by its id using Service layer
    // Instead of RequestMapping, we can use GetMapping
    @GetMapping(path = "/get/{id}")
    public ResponseEntity<Room> getRoomById(@Valid @PathVariable int id) {

            return new ResponseEntity<>(roomService.getRoombyId(id), HttpStatus.OK);

    }

    //	getAllRooms() method/endpoint handles a GET request to get All the rooms using methods in servcie layer
    @GetMapping("/get")
    public ResponseEntity<List<Room>> getAllRooms() {
        List<Room> roomList = roomService.getAllRoom();
        return new ResponseEntity<>(roomList, HttpStatus.OK);
    }

    // updateRoom() method handles the patch request and is used to update the room
    // details using the service method updateRoom
    // Here, we can use PatchMapping instead of RequestMapping to match our request.


    @PatchMapping("/update/{id}")
    public ResponseEntity<Room> updateRoom(@PathVariable int id,@RequestBody Room room) {

            return new ResponseEntity<>(roomService.updateRoomByFields(id, room), HttpStatus.OK);
    }
}