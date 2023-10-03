package com.hotel.service.impl;

import com.hotel.model.Customers;
import com.hotel.repository.CustomerRepository;
import com.hotel.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public List<Customers> getCustomerByName(String firstName, String lastName){

        return repository.findByFirstNameAndLastName(firstName,lastName);
    }

    @Override
    public Customers updateCustomer(int id, Customers customers) {

        Optional<Customers> existingCustomerOpt = repository.findById(id);

        Customers existingCustomer = null;

        if(existingCustomerOpt.isPresent()){
            existingCustomer = existingCustomerOpt.get();

             if(customers.getCity()!=null){
                existingCustomer.setCity(customers.getCity());
             }
             if(customers.getCountry()!=null){
                existingCustomer.setCountry(customers.getCountry());
            }
             if(customers.getState()!=null){
                existingCustomer.setState(customers.getState());
            }
             if(customers.getFirstName()!=null){
                existingCustomer.setFirstName(customers.getFirstName());
            }
             if(customers.getLastName()!=null){
                existingCustomer.setLastName(customers.getLastName());
            }
             if(customers.getZip()!=null){
                existingCustomer.setZip(customers.getZip());
            }
             if(customers.getEmailAddress()!=null) {
                existingCustomer.setEmailAddress(customers.getEmailAddress());
            }
             if(customers.getPreferences()!=null){
                existingCustomer.setPreferences(customers.getPreferences());
            }
             if(customers.getAdditionalAddressInfo()!=null){
                existingCustomer.setAdditionalAddressInfo(customers.getAdditionalAddressInfo());
            }
             if(customers.getStreetAddress()!=null){
                existingCustomer.setStreetAddress(customers.getStreetAddress());
            }
             if(customers.getPhoneNumber()!=null){
                existingCustomer.setPhoneNumber(customers.getPhoneNumber());
            }

             repository.save(existingCustomer);
        }
        return existingCustomer;
    }


}
