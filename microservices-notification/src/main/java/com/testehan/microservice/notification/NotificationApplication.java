package com.testehan.microservice.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication(
        scanBasePackages = {
                "com.testehan.microservice.notification",
                "com.testehan.microservice.amqp"
        }
)
// we need the below annotation in order to know if we want to use "default" or "kube" profile,
// so that the correct file is used "clients-default.properties" vs "clients-kube.properties"
@PropertySources({
        @PropertySource("classpath:clients-${spring.profiles.active}.properties")
})
public class NotificationApplication {

    public static void main( String[] args )
    {
        SpringApplication.run(NotificationApplication.class,args);
    }

//    @Bean
//    CommandLineRunner commandLineRunner(RabbitMQMessageProducer rabbitMQMessageProducer,
//                                        NotificationConfig notificationConfig){
//        return args -> {
//            rabbitMQMessageProducer.publish(
//                    "foo",
//                    notificationConfig.getInternalExchange(),
//                    notificationConfig.getInternalNotificationRoutingKey());
//        };
//    }
}
