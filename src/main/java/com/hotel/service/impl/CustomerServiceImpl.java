package com.hotel.service.impl;

import com.hotel.model.Customer;
import com.hotel.repository.CustomerRepository;
import com.hotel.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


/**
 * The CustomerServiceImpl class implements the CustomerService interface and provides
 * the business logic for managing customer data. It interacts with the CustomerRepository
 * to perform CRUD operations on customer records.
 *
 * @author Abdul Basith
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository repository;

    @Override
    public Customer createCustomer(Customer customer) {

        return repository.save(customer);

    }

    @Override
    public List<Customer> getAllCustomers() {
        return repository.findAll();
    }

    @Override
    public Customer getCustomerById(int id) {

        return repository.findById(id).get();
    }

    public List<Customer> getCustomerByName(String firstName, String lastName) {

        return repository.findByFirstNameAndLastName(firstName, lastName);
    }

    @Override
    public Customer updateCustomer(int id, Customer customer) {

        Optional<Customer> existingCustomerOpt = repository.findById(id);

        Customer existingCustomer = null;

        if (existingCustomerOpt.isPresent()) {
            existingCustomer = existingCustomerOpt.get();

            if (customer.getCity() != null) {
                existingCustomer.setCity(customer.getCity());
            }
            if (customer.getCountry() != null) {
                existingCustomer.setCountry(customer.getCountry());
            }
            if (customer.getState() != null) {
                existingCustomer.setState(customer.getState());
            }
            if (customer.getFirstName() != null) {
                existingCustomer.setFirstName(customer.getFirstName());
            }
            if (customer.getLastName() != null) {
                existingCustomer.setLastName(customer.getLastName());
            }
            if (customer.getZip() != null) {
                existingCustomer.setZip(customer.getZip());
            }
            if (customer.getEmailAddress() != null) {
                existingCustomer.setEmailAddress(customer.getEmailAddress());
            }
            if (customer.getPreferences() != null) {
                existingCustomer.setPreferences(customer.getPreferences());
            }
            if (customer.getAdditionalAddressInfo() != null) {
                existingCustomer.setAdditionalAddressInfo(customer.getAdditionalAddressInfo());
            }
            if (customer.getStreetAddress() != null) {
                existingCustomer.setStreetAddress(customer.getStreetAddress());
            }
            if (customer.getPhoneNumber() != null) {
                existingCustomer.setPhoneNumber(customer.getPhoneNumber());
            }

            repository.save(existingCustomer);
        }
        return existingCustomer;
    }

    @Override
    public List<Customer> getCustomerByPartialFirstName(String partialName) {

        return repository.findByFirstNameContaining(partialName);

    }


}
