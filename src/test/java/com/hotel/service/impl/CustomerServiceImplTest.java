package com.hotel.service.impl;

import com.hotel.model.Customer;
import com.hotel.repository.CustomerRepository;
import com.hotel.service.CustomerService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CustomerServiceImplTest {
    @Mock
    private CustomerRepository customerRepository;
    @InjectMocks
    private CustomerService customerService = new CustomerServiceImpl(customerRepository) ;

    private Customer customer;
    AutoCloseable autoCloseable;
    @BeforeEach
    void setUp() {

        customer = new Customer();
        customer.setFirstName("Robert");
        customer.setLastName("Williams");
        customer.setId(1);
        customer.setCity("Denver");
        customer.setZip("72204");
        customer.setEmailAddress("basith@gmail.com");
        customer.setPhoneNumber("5015214252");
        customer.setStreetAddress("1701 W Park");
        customer.setState("AR");
        customer.setCountry("USA");
        customer.setAdditionalAddressInfo("XYZ");

        autoCloseable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void testCreateCustomer() {
        mock(Customer.class);
        mock(CustomerRepository.class);

        when(customerRepository.save(customer)).thenReturn(customer);

        assertThat(customerService.createCustomer(customer).getLastName()).isEqualTo(customer.getLastName());
    }

    @Test
    void testGetAllCustomers() {
        mock(Customer.class);
        mock(CustomerRepository.class);

        when(customerRepository.findAll()).thenReturn(new ArrayList<>(Collections.singleton(customer)));

        assertThat(customerService.getAllCustomers().get(0).getId()).isEqualTo(customer.getId());
    }

    @Test
    void testGetCustomerById() {

        mock(Customer.class);
        mock(CustomerRepository.class);

        when(customerRepository.findById(1)).thenReturn(Optional.ofNullable(customer));

        assertThat(customerService.getCustomerById(1).getFirstName()).isEqualTo(customer.getFirstName());
    }

    @Test
    void testGetCustomerByName() {
        mock(Customer.class);
        mock(CustomerRepository.class);

        when(customerRepository.findByFirstNameAndLastName("Robert","Williams")).thenReturn(new ArrayList<>(Collections.singleton(customer)));

        assertThat(customerService.getCustomerByName("Robert","Williams").get(0).getFirstName()).isEqualTo(customer.getFirstName());
    }

    @Test
    void testUpdateCustomer() {
        mock(Customer.class);
        mock(CustomerRepository.class);

        when(customerRepository.findById(1)).thenReturn(Optional.of(customer));
        when(customerRepository.save(customer)).thenReturn(customer);

        assertThat(customerService.updateCustomer(1,customer).getCity()).isEqualTo(customer.getCity());
    }

    @Test
    void testGetCustomerByPartialFirstName() {
        mock(Customer.class);
        mock(CustomerRepository.class);

        when(customerRepository.findByFirstNameContaining("Robe")).thenReturn(new ArrayList<>(Collections.singleton(customer)));

        assertThat(customerService.getCustomerByPartialFirstName("Robe").get(0).getFirstName()).isEqualTo(customer.getFirstName());
    }
}