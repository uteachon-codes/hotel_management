package com.hotel.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.hotel.exception.EntityNotFoundException;
import com.hotel.model.Customer;
import com.hotel.service.CustomerService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerInfoController.class)
class CustomerInfoControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    private CustomerService customerService;

    Customer customerOne;
    Customer customerTwo;

    List<Customer> customerList = new ArrayList<>();
    

    @BeforeEach
    void setUp() {

        customerOne = new Customer();
        customerOne.setFirstName("Robert");
        customerOne.setLastName("Williams");
        customerOne.setId(1);
        customerOne.setCity("Denver");
        customerOne.setZip("72204");
        customerOne.setEmailAddress("basith@gmail.com");
        customerOne.setPhoneNumber("5015214252");
        customerOne.setStreetAddress("1701 W Park");
        customerOne.setState("AR");
        customerOne.setCountry("USA");
        customerOne.setAdditionalAddressInfo("XYZ");

        customerTwo = new Customer();
        customerTwo.setFirstName("Stuard");
        customerTwo.setLastName("Marsh");
        customerTwo.setId(2);
        customerTwo.setCity("LR");
        customerTwo.setZip("72012");
        customerTwo.setEmailAddress("srtuard@gmail.com");
        customerTwo.setPhoneNumber("4152415251");
        customerTwo.setStreetAddress("1601 Park");
        customerTwo.setState("AZ");
        customerTwo.setCountry("USA");
        customerTwo.setAdditionalAddressInfo("PPPYYYZZZ");

        customerList.add(customerOne);
        customerList.add(customerTwo);
    }

    @AfterEach
    void tearDown() {
        customerList.clear();
        customerOne = null;
        customerTwo = null;
    }

    @Test
    void testCreateCustomers() throws Exception{
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE,false);

        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String jsonData =  ow.writeValueAsString(customerOne);

       when(customerService.createCustomer(customerOne)).thenReturn(customerOne);

       this.mockMvc.perform(post("/customer/create")
                   .contentType(MediaType.APPLICATION_JSON)
                   .content(jsonData))
                   .andDo(print())
                   .andExpect(status().isOk());
    }

    @Test
    void testGetCustomers_Id() throws Exception {

        // Checks both found and not found

        when(customerService.getCustomerById(1)).thenReturn(customerOne);
        when(customerService.getCustomerById(52)).thenThrow(new EntityNotFoundException("Customer with id 52 not present",1520));

        this.mockMvc.perform(get("/customer/get/52"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
        this.mockMvc.perform(get("/customer/get/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void testGetCustomersWhenCustomersAvailable() throws Exception{

      when(customerService.getAllCustomers()).thenReturn(customerList);

      this.mockMvc.perform(get("/customer/get"))
              .andDo(print())
              .andExpect(status().isOk());
    }
    @Test
    void testGetCustomersWhenNoCustomers() throws Exception{

        when(customerService.getAllCustomers()).thenThrow(new EntityNotFoundException("no customers list",0));

        this.mockMvc.perform(get("/customer/get"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    void testGetCustomerByName_Found() throws Exception {

        when(customerService.getCustomerByName("Robert","Williams")).thenReturn(new ArrayList<>(Collections.singleton(customerOne)));

        this.mockMvc.perform(get("/customer/get/Robert/Williams"))
                .andDo(print())
                .andExpect(status().isOk());
    }
    @Test
    void testGetCustomerByName_Not_Found() throws Exception {

        when(customerService.getCustomerByName("Shami","Maise"))
                .thenThrow(new EntityNotFoundException("Customer with given firstname and last name" , 0));

        this.mockMvc.perform(get("/customer/get/Shami/Maise"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    void testUpdateCustomers() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE,false);

        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String jsonData =  ow.writeValueAsString(customerOne);

        when(customerService.updateCustomer(1,customerOne )).thenReturn(customerTwo);

        this.mockMvc.perform(patch("/customer/update/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonData))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void testGetCustomerByPartialFirstName_Found() throws Exception {
        when(customerService.getCustomerByPartialFirstName("Robe")).thenReturn(new ArrayList<>(Collections.singleton(customerOne)));

        this.mockMvc.perform(get("/customer/getFirst/Robe"))
                .andDo(print())
                .andExpect(status().isOk());
    }
    @Test
    void testGetCustomerByPartialFirstName_Not_Found() throws Exception {
        when(customerService.getCustomerByPartialFirstName("Mayt"))
                .thenThrow(new EntityNotFoundException("Customer with given partial name", 0));

        this.mockMvc.perform(get("/customer/getFirst/Mayt"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
}