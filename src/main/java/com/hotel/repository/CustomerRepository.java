package com.hotel.repository;


import com.hotel.model.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customers, Integer> {

    List<Customers> findByFirstNameAndLastName(String firstNameValue, String lastNameValue);
}
