package com.hotel.service;

import com.hotel.model.Customers;

import java.util.List;

public interface CustomerService {
    public Customers createCustomer(Customers customers);

    public List<Customers> getAllCustomers();

    public Customers getCustomerById(int id);

    public List<Customers> getCustomerByName(String firstName, String lastName);
    public Customers updateCustomer(int id,Customers customers);
}
