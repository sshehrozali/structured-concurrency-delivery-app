package com.fooddelivery;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.concurrent.StructuredTaskScope;

@Service
public class OrderService {

    private final RestClient restClient;
    private final MessagingBroker messagingBroker;

    public OrderService(MessagingBroker messagingBroker) {
        this.restClient = RestClient.builder()
                .baseUrl("")
                .build();

        this.messagingBroker = messagingBroker;
    }

    public boolean newOrder(String orderId) throws InterruptedException {

        var scope = StructuredTaskScope.open();
        var assignRestaurantTask = scope.fork(this::callRestaurantService);
        var assignRiderTask = scope.fork(() -> messagingBroker.publish("area XYZ"));

        scope.join();

        return assignRestaurantTask.get();
    }

    private Boolean callRestaurantService() {
        return restClient.post()
                .uri("")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(Boolean.class);
    }
}
