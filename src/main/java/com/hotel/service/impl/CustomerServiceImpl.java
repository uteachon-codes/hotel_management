package com.hotel.service.impl;

import com.hotel.model.Customers;
import com.hotel.repository.CustomerRepository;
import com.hotel.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository repository;
    @Override
    public Customers createCustomer(Customers customers) {

        return repository.save(customers);

    }

    @Override
    public List<Customers> getAllCustomers() {
        return repository.findAll();
    }

    @Override
    public Customers getCustomerById(int id) {

        return repository.findById(id).get();
    }


}
