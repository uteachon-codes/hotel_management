package com.hotel.controller;

import com.hotel.model.Customers;
import com.hotel.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping(value = "/customer")
public class CustomerInfoController {

    @Autowired
    private CustomerService customerService;


    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public ResponseEntity<Customers> createCustomers(@RequestBody Customers customers){
        return new ResponseEntity<>(customerService.createCustomer(customers), HttpStatus.OK) ;
    }

    @RequestMapping(path = "/get", method = RequestMethod.GET)
    public ResponseEntity<List<Customers>> getCustomers(){
        return new ResponseEntity<List<Customers>>(customerService.getAllCustomers(),HttpStatus.OK);
    }


    @RequestMapping(path = "/get/{id}",method= RequestMethod.GET)
    public ResponseEntity<Customers> getCustomers(@PathVariable int id){
        return new ResponseEntity<Customers>(customerService.getCustomerById(id),HttpStatus.OK);
    }
}
