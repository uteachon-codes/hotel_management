package com.hotel.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "customer")
@Data
@DynamicInsert
@DynamicUpdate
public class Customers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="phone_number")
    private String phoneNumber;

    @Column(name="email_address")
    private String emailAddress;

    @Column(name="street_address")
    private String streetAddress;

    @Column(name="additional_address_info")
    private String additionalAddressInfo;

    private String city;

    private String state;

    private String zip;

    private String country;

    private String preferences;
}
