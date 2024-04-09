package com.hotel.controller;

import com.hotel.exception.EntityNotFoundException;
import com.hotel.model.Customer;
import com.hotel.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ROLE_MANAGER')" )
    public ResponseEntity<Customer> createCustomers(@Valid @RequestBody Customer customer) {
        return new ResponseEntity<>(customerService.createCustomer(customer), HttpStatus.OK);
    }

    @GetMapping("/get")
    @PreAuthorize("hasAuthority('ROLE_USER') || hasAuthority('ROLE_MANAGER')" )
    public ResponseEntity<List<Customer>> getCustomers() {
        List<Customer> allCustomers = customerService.getAllCustomers();
        if(allCustomers.isEmpty()){
            throw new EntityNotFoundException("No Customer Records Available",0);
        }else {
        return new ResponseEntity<List<Customer>>(allCustomers, HttpStatus.OK);
        }
    }


    @GetMapping("/get/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER') || hasAuthority('ROLE_MANAGER')" )
    public ResponseEntity<Customer> getCustomers(@PathVariable int id) {
        Customer customer;
        try {
            customer = customerService.getCustomerById(id);
        } catch (NoSuchElementException e) {
            throw new EntityNotFoundException("No customer exists with the given id", id);
        }
        return new ResponseEntity<Customer>(customer, HttpStatus.OK);

    }

    @GetMapping("/get/{firstName}/{lastName}")
    @PreAuthorize("hasAuthority('ROLE_USER') || hasAuthority('ROLE_MANAGER')" )
    public ResponseEntity<List<Customer>> getCustomerByName(@PathVariable String firstName, @PathVariable String lastName) {

        List<Customer> customerByName = customerService.getCustomerByName(firstName, lastName);

        if (customerByName.isEmpty()) {
            throw new EntityNotFoundException("Customer with given firstname " + firstName + " and last name " + lastName + " was not found ", 0);
        }
        return new ResponseEntity<List<Customer>>(customerByName, HttpStatus.OK);
    }

    @PatchMapping("/update/{id}")
    @PreAuthorize("hasAuthority('ROLE_MANAGER')" )
    public ResponseEntity<Customer> updateCustomers(@PathVariable int id, @RequestBody Customer customer) {

        return new ResponseEntity<Customer>(customerService.updateCustomer(id, customer), HttpStatus.OK);
    }

    @GetMapping("/getFirst/{partialFirstName}")
    @PreAuthorize("hasAuthority('ROLE_USER') || hasAuthority('ROLE_MANAGER')" )
    public ResponseEntity<List<Customer>> getCustomerByPartialFirstName(@PathVariable String partialFirstName) {
        List<Customer> customerByName = customerService.getCustomerByPartialFirstName(partialFirstName);
        if (customerByName.isEmpty()) {
            throw new EntityNotFoundException("Customer with given partial name " + partialFirstName + " was not found ", 0);
        }

        return new ResponseEntity<List<Customer>>(customerByName, HttpStatus.OK);
    }

}