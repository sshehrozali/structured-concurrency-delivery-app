package com.fooddelivery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

public class OrderService {

    @Autowired
    private RestTemplate restTemplate;

    private final MessagingBroker messagingBroker;

    public OrderService(MessagingBroker messagingBroker) {
        this.messagingBroker = messagingBroker;
    }

    public void newOrder(String orderId) {
        // 1. call HTTP restaurant service
        var response = restTemplate.postForEntity("restaurant api", orderId, String.class);

        // 2. publish message to assign rider
        messagingBroker.publish();
    }
}
