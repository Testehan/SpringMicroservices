package com.testehan.microservice.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public void registerCustomer(CustomerRegistrationRequest customerRequest) {
        var customer = Customer.builder()
                .firstName(customerRequest.firstName())
                .lastName(customerRequest.lastName())
                .email(customerRequest.email())
                .build();
        // todo check if email is valid, not taken and then store custom in DB
        customerRepository.save(customer);
    }
}
