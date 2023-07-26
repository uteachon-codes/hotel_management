package com.hotel.service;

import com.hotel.exception.BusinessException;
import com.hotel.exception.SystemException;
import com.hotel.model.Room;

import java.util.List;
import java.util.Map;

public interface RoomService {

    public Room createRoom(Room room) throws BusinessException, SystemException;

    public Room getRoombyId(int id) throws BusinessException, SystemException;

    public List<Room> getAllRoom() throws SystemException;

    Room updateRoomByFields(int id, Map<String, Object> fields) throws BusinessException, SystemException;

}