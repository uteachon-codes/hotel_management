package com.hotel.controller;

import com.hotel.exception.CustomerNotFoundException;
import com.hotel.model.Customer;
import com.hotel.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * CustomerInfoController is a controller responsible for handling HTTP requests
 * related to customer information, including creation, retrieval, updating, and searching for customers.
 * It serves as the RESTful API endpoint for managing customer data.
 *
 * @author Abdul Basith
 */
@RestController
@Validated
@RequestMapping(value = "/customer")
public class CustomerInfoController {

    @Autowired
    private CustomerService customerService;


    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public ResponseEntity<Customer> createCustomers(@Valid @RequestBody Customer customer) {
        return new ResponseEntity<>(customerService.createCustomer(customer), HttpStatus.OK);
    }

    @RequestMapping(path = "/get", method = RequestMethod.GET)
    public ResponseEntity<List<Customer>> getCustomers() {
        return new ResponseEntity<List<Customer>>(customerService.getAllCustomers(), HttpStatus.OK);
    }


    @RequestMapping(path = "/get/{id}", method = RequestMethod.GET)
    public ResponseEntity<Customer> getCustomers(@PathVariable int id) {
        Customer customer;
        try {
            customer = customerService.getCustomerById(id);
        } catch (NoSuchElementException e) {
            throw new CustomerNotFoundException("No customer exists with the given id", id);
        }
        return new ResponseEntity<Customer>(customer, HttpStatus.OK);

    }

    @RequestMapping(path = "/get/{firstName}/{lastName}", method = RequestMethod.GET)
    public ResponseEntity<List<Customer>> getCustomerByName(@PathVariable String firstName, @PathVariable String lastName) {

        List<Customer> customerByName = customerService.getCustomerByName(firstName, lastName);

        if (customerByName.isEmpty()) {
            throw new CustomerNotFoundException("Customer with given firstname " + firstName + " and last name " + lastName + " was not found ", 000);
        }
        return new ResponseEntity<List<Customer>>(customerByName, HttpStatus.OK);
    }

    @RequestMapping(path = "/update/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<Customer> updateCustomers(@PathVariable int id, @RequestBody Customer customer) {

        return new ResponseEntity<Customer>(customerService.updateCustomer(id, customer), HttpStatus.OK);
    }
}
