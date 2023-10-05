package com.hotel.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.Date;

@Data
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name="reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "room_id")
    private int roomId;

    @Min(value = 0,message = "Customer Id is must")
    @Column(name="customer_id")
    private int customerId;

    @JsonIgnore
    @Temporal(TemporalType.DATE)
    @Column(name = "reservation_date")
    private Date reservationDate;

    @JsonIgnore
    @Temporal(TemporalType.DATE)
    @Column(name="update_date")
    private Date updateDate;

    @Future(message = "Please provide a future Check in date")
    @Temporal(TemporalType.DATE)
    @Column(name="check_in_date")
    private Date checkInDate;

    @Future(message = "Please provide a future Check out date")
    @Temporal(TemporalType.DATE)
    @Column(name="check_out_date")
    private Date checkOutDate;

    @Column(name="room_key")
    private String roomKey;

    @Column(name="payment_mode")
    private int paymentMode;

    @Column(name="reservation_mode")
    private int reservationMode;
}