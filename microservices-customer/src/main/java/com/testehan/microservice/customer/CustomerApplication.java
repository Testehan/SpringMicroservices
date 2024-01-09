package com.testehan.microservice.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(
        scanBasePackages = {
                "com.testehan.microservice.customer",
                "com.testehan.microservice.amqp",
                "com.testehan.microservice.clients.fraud"
        }
)
@EnableFeignClients(
        basePackages = "com.testehan.microservice.clients"
)
public class CustomerApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(CustomerApplication.class,args);
    }
}
