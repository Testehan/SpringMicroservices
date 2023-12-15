package com.testehan.microservice.customer;

public record CustomerRegistrationRequest(String firstName,
                                          String lastName,
                                          String email) {
}
