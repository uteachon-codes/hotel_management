package com.hotel.repository;

import com.hotel.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//It provides ready-to-use methods for performing CRUD operations(Create,Read,Update,Delete) 
//on Room entities using an Integer identifier.

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
}