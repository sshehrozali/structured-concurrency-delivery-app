package com.fooddelivery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CustomerService {

    @Autowired
    private RestTemplate restTemplate;

    public void placeOrder() {
        // call HTTP order service
        var response = restTemplate
                .getForEntity("order api", String.class);
    }
}
