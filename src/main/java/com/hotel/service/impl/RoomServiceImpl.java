package com.hotel.service.impl;

import com.hotel.model.Room;
import com.hotel.repository.RoomRepository;
import com.hotel.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class RoomServiceImpl implements RoomService {

    private RoomRepository roomRepository;

    @Autowired
    public RoomServiceImpl(RoomRepository roomRepository) {
        super();
        this.roomRepository = roomRepository;
    }

    @Override
    public Room createRoom(Room room) {

        LocalDate currentDate = LocalDate.now();

        java.sql.Date sqlDate = java.sql.Date.valueOf(currentDate);
        room.setCreateDate(sqlDate);
        return roomRepository.save(room);

    }

    @Override
    public Room getRoombyId(int id) {
        Optional<Room> optionalRoom = roomRepository.findById(id);
        Room room = optionalRoom.get();
        return room;
    }

    @Override
    public List<Room> getAllRoom() {

        List<Room> rooms = roomRepository.findAll();
        return rooms;
    }

    // updateRoomByFields() method gets the new room details in the newRoom object and the
    // id of the room to be changed. It gets the existing/old room object
    // from the db.
    // Updates the old room with the new room details
    @Override
    public Room updateRoomByFields(int id, Map<String, Object> fields) {

        Optional<Room> existingRoom = roomRepository.findById(id);

        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Room.class, key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, existingRoom.get(), value);

        });
        return roomRepository.save(existingRoom.get());
    }


}
