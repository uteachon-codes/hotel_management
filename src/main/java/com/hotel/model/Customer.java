package com.hotel.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "First Name cannot be left blank")
    @Column(name="first_name")
    private String firstName;

    @NotBlank(message = "Last Name cannot be left blank")
    @Column(name="last_name")
    private String lastName;

    @Size(min = 10, max = 13, message = "Phone number should be between 10 t0 13 characters")
    @Column(name="phone_number")
    private String phoneNumber;

    @Email(message = "Accepted Email format is joe@xyz.com")
    @Column(name="email_address")
    private String emailAddress;

    @NotBlank(message = "Street address cannot be left blank")
    @Column(name="street_address")
    private String streetAddress;

    @Column(name="additional_address_info")
    private String additionalAddressInfo;

    @NotBlank(message = "City cannot be left blank")
    private String city;

    @NotBlank(message = "State cannot be left blank")
    private String state;

    @NotBlank(message = "Zip cannot be left blank")
    private String zip;

    private String country;

    private String preferences;
}
