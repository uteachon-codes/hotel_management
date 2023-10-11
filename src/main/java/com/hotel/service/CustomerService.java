package com.hotel.service;

import com.hotel.model.Customer;

import java.util.List;


/**
 * The CustomerService interface defines a set of methods for managing customer data.
 * Implementing classes provide the actual business logic and data access operations.
 *
 * @author Abdul Basith
 */
public interface CustomerService {
    public Customer createCustomer(Customer customer);

    public List<Customer> getAllCustomers();

    public Customer getCustomerById(int id);

    public List<Customer> getCustomerByName(String firstName, String lastName);
    public Customer updateCustomer(int id, Customer customer);

    public List<Customer> getCustomerByPartialFirstName(String partialName);
}
