package com.fooddelivery;

import org.springframework.stereotype.Component;

@Component
public class DummyKafkaProducer implements MessagingBroker{

    @Override
    public void publish(String location) {
        // publish message on kafka
    }
}
