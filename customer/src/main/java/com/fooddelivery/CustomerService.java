package com.fooddelivery;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class CustomerService {

    private final RestClient restClient;

    public CustomerService() {
        this.restClient = RestClient.builder()
                .baseUrl("order-service/api/v1")
                .build();
    }


    public void placeOrder(String orderId) {
        // call HTTP order service
        var response = restClient
                .get()
                .uri("/new/order/{orderId}", orderId)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(String.class);
    }
}
