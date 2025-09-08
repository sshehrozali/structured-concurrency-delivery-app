package com.fooddelivery;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class RestaurantRestController {
    private final RestaurantService restaurantService;

    public RestaurantRestController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @PostMapping("/receive/order/{orderId}")
    public ResponseEntity<Boolean> receiveOrder(@PathVariable String orderId) throws InterruptedException {
        return ResponseEntity.ok(restaurantService.assignOrder(orderId));
    }
}
