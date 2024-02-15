package com.hotel.service;

import com.hotel.model.Room;

import java.util.List;


/**
 * RoomService is an interface defining the contract for managing room-related operations.
 *
 * @author Abdul Basith
 */
public interface RoomService {

    public Room createRoom(Room room);

    public Room getRoombyId(int id);

    public List<Room> getAllRoom();

    Room updateRoomByFields(int id, Room room);

}