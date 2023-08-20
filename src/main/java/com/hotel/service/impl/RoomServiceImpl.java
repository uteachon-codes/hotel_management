package com.hotel.service.impl;

import com.hotel.model.Room;
import com.hotel.repository.RoomRepository;
import com.hotel.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
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
    public Room createRoom(Room room)  {
            Date currentDate = new Date();
            room.setCreateDate(currentDate);
            return roomRepository.save(room);
    }

    @Override
    public Room getRoombyId(int id) {
        Optional<Room> optionalRoom = roomRepository.findById(id);
        Room room = optionalRoom.get();
        return room;
    }

    @Override
    public List<Room> getAllRoom()  {

        List<Room> rooms = roomRepository.findAll();
        return rooms;

    }



    // updateRoomByFields() method gets the new room details in the newRoom object and the
    // id of the room to be changed. It gets the existing/old room object
    // from the db.
    // Updates the old room with the new room details

    public Room updateRoomByFields(int id, Room room) {

            Optional<Room> existingRoomOpt = roomRepository.findById(id);

            Room existingRoom = null;

            if(existingRoomOpt.isPresent() ){
                existingRoom = existingRoomOpt.get();
                if(  room.getRoomType() !=null) {
                    existingRoom.setRoomType(room.getRoomType());
                }
                if(room.getRoomNumber() !=null) {
                    existingRoom.setRoomNumber(room.getRoomNumber());
                }
                if(room.getStatus() !=null) {
                    existingRoom.setStatus(room.getStatus());
                }
                if(room.getAmenities() !=null) {
                    existingRoom.setAmenities(room.getAmenities());
                }
                if(room.getMaxOccupancy() != 0 ){
                    existingRoom.setMaxOccupancy(room.getMaxOccupancy());
                }

                room.setUpdateDate(new Date());
                roomRepository.save(existingRoom);

            }

           return existingRoom;
   }
}
