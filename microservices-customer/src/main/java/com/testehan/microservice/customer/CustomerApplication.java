package com.testehan.microservice.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

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

// we need the below annotation in order to know if we want to use "default" or "kube" profile,
// so that the correct file is used "clients-default.properties" vs "clients-kube.properties"
@PropertySources({
        @PropertySource("classpath:clients-${spring.profiles.active}.properties")
})
public class CustomerApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(CustomerApplication.class,args);
    }
}
