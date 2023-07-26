package com.hotel.service.impl;

import com.hotel.exception.BusinessException;
import com.hotel.exception.SystemException;
import com.hotel.model.Room;
import com.hotel.repository.RoomRepository;
import com.hotel.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.*;

@Service
public class RoomServiceImpl implements RoomService {

    private RoomRepository roomRepository;

    @Autowired
    public RoomServiceImpl(RoomRepository roomRepository) {
        super();
        this.roomRepository = roomRepository;
    }

    @Override
    public Room createRoom(Room room) throws BusinessException, SystemException {
        try {
            Date currentDate = new Date();
            room.setCreateDate(currentDate);
            return roomRepository.save(room);
        }catch(DataIntegrityViolationException e){
            String errorMsg = String.format("Room number %s already exists !!",room.getRoomNumber());
            throw new BusinessException("110",errorMsg);
        }catch(Exception e){
            throw new SystemException("700","Something went wrong !!");
        }
    }

    @Override
    public Room getRoombyId(int id) throws BusinessException, SystemException {
        Optional<Room> optionalRoom = roomRepository.findById(id);
        Room room = null;
        try{
            room = optionalRoom.get();
        }catch(NoSuchElementException e){
            throw new BusinessException("100","Room Not Found");
        }catch(DataAccessException e ){
            throw new SystemException("500","Error Connecting DB !!");
        }catch(Exception e){
            throw new SystemException("700","Something went wrong !!");
        }

        return room;
    }

    @Override
    public List<Room> getAllRoom() throws SystemException {

        try{
        List<Room> rooms = roomRepository.findAll();
        return rooms;
        }catch(Exception e ){
            String errorMsg = e.getMessage();
            throw new SystemException("700",errorMsg);
        }
    }



    // updateRoomByFields() method gets the new room details in the newRoom object and the
    // id of the room to be changed. It gets the existing/old room object
    // from the db.
    // Updates the old room with the new room details
    @Override
    public Room updateRoomByFields(int id, Map<String, Object> fields) throws BusinessException, SystemException {
        try {
            Optional<Room> existingRoom = roomRepository.findById(id);
            fields.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(Room.class, key);
                field.setAccessible(true);
                ReflectionUtils.setField(field, existingRoom.get(), value);

            });
            existingRoom.get().setUpdateDate(new Date());
            return roomRepository.save(existingRoom.get());
        }catch(NoSuchElementException e ){
            throw new BusinessException("100","Room Not Found");
        } catch(Exception e){
            String errorMsg = e.getMessage();
            throw new SystemException("700",errorMsg);
        }

    }
}
