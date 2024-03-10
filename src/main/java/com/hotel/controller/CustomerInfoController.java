package com.hotel.controller;

import com.hotel.exception.EntityNotFoundException;
import com.hotel.model.Customer;
import com.hotel.model.User;
import com.hotel.model.UserModel;
import com.hotel.service.CustomerService;
import com.hotel.service.UserService;
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

    @Autowired
    private UserService userService;
    @RequestMapping(path = "/usercreate",method = RequestMethod.POST)
    public String registerUser(@RequestBody UserModel userModel) {
        User user = userService.registerUser(userModel);
        return "Success";
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public ResponseEntity<Customer> createCustomers(@Valid @RequestBody Customer customer) {
        return new ResponseEntity<>(customerService.createCustomer(customer), HttpStatus.OK);
    }

    @RequestMapping(path = "/get", method = RequestMethod.GET)
    public ResponseEntity<List<Customer>> getCustomers() {
        List<Customer> allCustomers = customerService.getAllCustomers();
        if(allCustomers.isEmpty()){
            throw new EntityNotFoundException("No Customer Records Available",0);
        }else {
        return new ResponseEntity<List<Customer>>(allCustomers, HttpStatus.OK);
        }
    }


    @RequestMapping(path = "/get/{id}", method = RequestMethod.GET)
    public ResponseEntity<Customer> getCustomers(@PathVariable int id) {
        Customer customer;
        try {
            customer = customerService.getCustomerById(id);
        } catch (NoSuchElementException e) {
            throw new EntityNotFoundException("No customer exists with the given id", id);
        }
        return new ResponseEntity<Customer>(customer, HttpStatus.OK);

    }

    @RequestMapping(path = "/get/{firstName}/{lastName}", method = RequestMethod.GET)
    public ResponseEntity<List<Customer>> getCustomerByName(@PathVariable String firstName, @PathVariable String lastName) {

        List<Customer> customerByName = customerService.getCustomerByName(firstName, lastName);

        if (customerByName.isEmpty()) {
            throw new EntityNotFoundException("Customer with given firstname " + firstName + " and last name " + lastName + " was not found ", 0);
        }
        return new ResponseEntity<List<Customer>>(customerByName, HttpStatus.OK);
    }

    @RequestMapping(path = "/update/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<Customer> updateCustomers(@PathVariable int id, @RequestBody Customer customer) {

        return new ResponseEntity<Customer>(customerService.updateCustomer(id, customer), HttpStatus.OK);
    }

    @RequestMapping(path = "/getFirst/{partialFirstName}", method = RequestMethod.GET)
    public ResponseEntity<List<Customer>> getCustomerByPartialFirstName(@PathVariable String partialFirstName) {
        List<Customer> customerByName = customerService.getCustomerByPartialFirstName(partialFirstName);
        if (customerByName.isEmpty()) {
            throw new EntityNotFoundException("Customer with given partial name " + partialFirstName + " was not found ", 0);
        }

        return new ResponseEntity<List<Customer>>(customerByName, HttpStatus.OK);
    }

}