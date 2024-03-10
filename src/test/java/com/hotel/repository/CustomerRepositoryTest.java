package com.hotel.repository;

import com.hotel.model.Customer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    Customer customer;

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
        customerRepository.save(customer);

    }

    @AfterEach
    void tearDown() {

        customer = null;
    }

    @Test
    void testFindByFirstNameAndLastName_Found() {
        assertThat(customerRepository.findByFirstNameAndLastName("Robert","Williams").get(0).getFirstName()).isEqualTo("Robert");
        assertThat(customerRepository.findByFirstNameAndLastName("Robert","Williams").get(0).getLastName()).isEqualTo("Williams");
    }
@Test
    void testFindByFirstNameAndLastName_Not_Found() {
        assertThat(customerRepository.findByFirstNameAndLastName("George","Suse").isEmpty()).isTrue();
        assertThat(customerRepository.findByFirstNameAndLastName("Rob","peter").isEmpty()).isTrue();
    }

    @Test
    void testFindByFirstNameContaining_Found() {

        assertThat(customerRepository.findByFirstNameContaining("Robe").get(0).getFirstName()).isEqualTo("Robert");
        assertThat(customerRepository.findByFirstNameContaining("Robert").get(0).getLastName()).isEqualTo("Williams");

    }
    @Test
    void testFindByFirstNameContaining_Not_Found() {

        assertThat(customerRepository.findByFirstNameContaining("Zeeb").isEmpty()).isTrue();
        assertThat(customerRepository.findByFirstNameContaining("Vitamin").isEmpty()).isTrue();

    }
}