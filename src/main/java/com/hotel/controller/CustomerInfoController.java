package com.hotel.controller;

import com.hotel.exception.CustomerNotFoundException;
import com.hotel.model.Customers;
import com.hotel.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@Validated
@RequestMapping(value = "/customer")
public class CustomerInfoController {

    @Autowired
    private CustomerService customerService;


    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public ResponseEntity<Customers> createCustomers(@Valid @RequestBody Customers customers){
        return new ResponseEntity<>(customerService.createCustomer(customers), HttpStatus.OK) ;
    }

    @RequestMapping(path = "/get", method = RequestMethod.GET)
    public ResponseEntity<List<Customers>> getCustomers(){
        return new ResponseEntity<List<Customers>>(customerService.getAllCustomers(),HttpStatus.OK);
    }


    @RequestMapping(path = "/get/{id}",method= RequestMethod.GET)
    public ResponseEntity<Customers> getCustomers(@PathVariable int id) {
        Customers customer;
        try {
            customer = customerService.getCustomerById(id);
        } catch (NoSuchElementException e) {
            throw new CustomerNotFoundException("No customer exists with the given id", id);
        }
        return new ResponseEntity<Customers>(customer,HttpStatus.OK);

    }

    @RequestMapping(path="/get/{firstName}/{lastName}",method= RequestMethod.GET)
    public ResponseEntity<List<Customers>> getCustomerByName(@PathVariable String firstName, @PathVariable String lastName){

            List<Customers> customerByName = customerService.getCustomerByName(firstName, lastName);

            if(customerByName.isEmpty()) {
                throw new CustomerNotFoundException("Customer with given firstname " + firstName + " and last name " + lastName + " was not found ", 000);
            }
            return new ResponseEntity<List<Customers>>(customerByName,HttpStatus.OK);
    }

    @RequestMapping(path="/update/{id}",method=RequestMethod.PATCH)
    public ResponseEntity<Customers> updateCustomers(@PathVariable int id,@RequestBody Customers customers){

        return new ResponseEntity<Customers>(customerService.updateCustomer(id,customers),HttpStatus.OK);
    }
}
