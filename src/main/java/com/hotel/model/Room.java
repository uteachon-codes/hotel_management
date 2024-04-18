package com.hotel.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hotel.repository.JsonMapConverter;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.Date;
import java.util.Map;

/**
 * This class is used as a Entity that represents a persistent data entity in a database.
 *
 * @author Abdul Basith
 */
@Entity
@Table(name = "room_info")
@Data
@DynamicInsert
@DynamicUpdate
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Room Number cannot be blank")
    @Column(name = "room_number", unique = true)
    private String roomNumber;

    @NotBlank(message = "Room type cannot be blank")
    @Column(name = "room_type")
    private String roomType;

    @NotBlank(message = "Status cannot be blank")
    private String status;

    @Min(value = 1, message = "min occupancy cannot be less than 1")
    @Max(value = 150, message = "Max Occupancy cannot be more than 150")
    @Column(name = "max_occupancy")
    private int maxOccupancy;

    @Column(name = "amenities", columnDefinition = "json")
    @Convert(converter = JsonMapConverter.class)
    private Map<String, Object> amenities;

    @JsonIgnore
    @Temporal(TemporalType.DATE)
    @Column(name = "create_date")
    private Date createDate;

    @JsonIgnore
    @Temporal(TemporalType.DATE)
    @Column(name = "update_date")
    private Date updateDate;


}
