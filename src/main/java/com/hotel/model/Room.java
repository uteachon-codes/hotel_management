package com.hotel.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.Date;

// This class is used as a Entity that represents a persistent data entity in a database.

@Entity
@Table(name = "room_info")
@Data
@DynamicInsert
@DynamicUpdate
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message ="Room Number cannot be blank")
    @Column(name = "room_number", unique = true)
    private String roomNumber;

    @NotBlank(message="Room type cannot be blank")
    @Column(name = "room_type")
    private String roomType;

    @NotBlank(message="Status cannot be blank")
    private String status;

    @Digits(integer = 3, message = "max 999 occupancy", fraction = 0)
    @Column(name = "max_occupancy")
    private int maxOccupancy;

    @Column(name = "amenities", columnDefinition = "json")
    private String amenities;

    @JsonIgnore
    @Temporal(TemporalType.DATE)
    @Column(name = "create_date")
    private Date createDate;

    @JsonIgnore
    @Temporal(TemporalType.DATE)
    @Column(name = "update_date")
    private Date updateDate;

}
