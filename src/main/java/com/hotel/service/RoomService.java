package com.hotel.service;

import com.hotel.model.Room;

import java.util.List;

public interface RoomService {

    public Room createRoom(Room room);

    public Room getRoombyId(int id);

    public List<Room> getAllRoom();

    Room updateRoomByFields(int id, Room room);

}