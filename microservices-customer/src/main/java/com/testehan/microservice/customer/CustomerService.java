package com.testehan.microservice.customer;

import com.testehan.microservice.amqp.RabbitMQMessageProducer;
import com.testehan.microservice.clients.fraud.FraudCheckResponse;
import com.testehan.microservice.clients.fraud.FraudClient;
import com.testehan.microservice.clients.notification.NotificationClient;
import com.testehan.microservice.clients.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;

    private final FraudClient fraudClient;
    private final NotificationClient notificationClient;
    private final RabbitMQMessageProducer rabbitMQMessageProducer;

    public void registerCustomer(CustomerRegistrationRequest customerRequest) {
        var customer = Customer.builder()
                .firstName(customerRequest.firstName())
                .lastName(customerRequest.lastName())
                .email(customerRequest.email())
                .build();
        // todo check if email is valid, not taken and then store custom in DB
        customerRepository.saveAndFlush(customer);

        // this is one way of calling the microservice
//        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
//                "http://FRAUD:8081/api/v1/fraud-check/{customerId}",
//                FraudCheckResponse.class,
//                customer.getId()
//                );

        // this is another way of calling the microservice
        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());

        if (fraudCheckResponse.isFraudster()){
            throw new IllegalStateException("fraudster");
        }

        NotificationRequest notificationRequest = new NotificationRequest(customer.getId(), customer.getEmail(),
                String.format("Hi %s, welcome to DanTe...", customer.getFirstName()));

        // below is the old approach of doing the operation synchronously
        //notificationClient.sendNotification(notificationRequest);

        // this is doing things Asynchronously
        rabbitMQMessageProducer.publish(notificationRequest,"internal.exchange","internal.notification.routing-key");
    }
}
