package com.hotel.repository;


import com.hotel.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * The CustomerRepository interface is a Spring Data JPA repository responsible for
 * performing database operations related to the Customer entity.
 * It extends JpaRepository, providing convenient methods for CRUD (Create, Read, Update, Delete) operations.
 *
 * @author Abdul Basith
 */

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    List<Customer> findByFirstNameAndLastName(String firstNameValue, String lastNameValue);


    List<Customer> findByFirstNameContaining(String partialName);
}
