package com.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotel.model.Room;

//It provides ready-to-use methods for performing CRUD operations(Create,Read,Update,Delete) 
//on Room entities using an Integer identifier.

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
}