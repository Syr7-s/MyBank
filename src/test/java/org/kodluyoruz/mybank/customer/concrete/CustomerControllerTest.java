package org.kodluyoruz.mybank.customer.concrete;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.kodluyoruz.mybank.utilities.enums.gender.Gender;
import org.springframework.web.client.RestTemplate;


import java.net.URI;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CustomerControllerTest {
    RestTemplate restTemplate;
    Customer customer = new Customer();

    @BeforeEach
    void setUp() {
        restTemplate = new RestTemplate();
    }

    @Test
    void create() {
        customer.setCustomerName("Ugur");
        customer.setCustomerLastname("Demir");
        customer.setCustomerGender(Gender.ERKEK);
        customer.setCustomerPhone("5359874512");
        customer.setCustomerEmail("ugurdemir7@gmail.com");
        customer.setCustomerAddress("ISTANBUL");
        customer.setCustomerBirthDate(LocalDate.of(1993, 4, 25));
        URI location = restTemplate.postForLocation("http://localhost:8080/api/v1/customer", customer);
        assert location != null;
        Customer editedCustomer = restTemplate.getForObject(location, Customer.class);
        assert editedCustomer != null;
        Assertions.assertEquals("Ugur", editedCustomer.getCustomerName());

    }

    @Test
    void getCustomerById() {
        Customer customer = restTemplate.getForObject("http://localhost:8080/api/v1/customer/94556701125", Customer.class);
        assert customer != null;
        assertEquals("Isa", customer.getCustomerName());
    }


}