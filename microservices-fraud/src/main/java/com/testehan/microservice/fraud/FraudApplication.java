package com.testehan.microservice.fraud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
// we need the below annotation in order to know if we want to use "default" or "kube" profile,
// so that the correct file is used "clients-default.properties" vs "clients-kube.properties"
@PropertySources({
        @PropertySource("classpath:clients-${spring.profiles.active}.properties")
})
public class FraudApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(FraudApplication.class,args);
    }
}
