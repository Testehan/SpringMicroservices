package com.testehan.microservice.clients.fraud;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// this will target the FraudController. so any microservice that wants to talk to FraudController, all they need to do is
// to use this interface

//@FeignClient(
//        value = "fraud"
//)


// made the change to below in order for the solution to work on kubernetes
@FeignClient(
        name = "fraud",
        url = "${clients.fraud.url}"
)
public interface FraudClient {

    @GetMapping(path = "api/v1/fraud-check/{customerId}")
    FraudCheckResponse isFraudster(@PathVariable("customerId") Integer customerId);
}
