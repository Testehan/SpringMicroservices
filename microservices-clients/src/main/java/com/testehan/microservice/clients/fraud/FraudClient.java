package com.testehan.microservice.clients.fraud;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// this will target the FraudController. so any microservice that wants to talk to FraudController, all they need to do is
// to use this interface

@FeignClient(
        value = "fraud",
        path = "api/v1/fraud-check"     // we can either specify this here, or provide in the @GetMapping annotation
)                                       // like  @GetMapping(path = "{api/v1/fraud-check/customerId}")
public interface FraudClient {

    @GetMapping(path = "{customerId}")
    FraudCheckResponse isFraudster(@PathVariable("customerId") Integer customerId);
}
