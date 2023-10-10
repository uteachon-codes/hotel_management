package com.hotel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.hotel.repository")
public class HotelManagementApplication {


    public static void main(String[] args) {

        SpringApplication.run(HotelManagementApplication.class, args);
    }
}