package com.fooddelivery;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class OrderRestController {
    private final OrderService orderService;

    public OrderRestController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/new/order/{orderId}")
    public ResponseEntity<Boolean> newOrder(@PathVariable String orderId) throws InterruptedException {
        return ResponseEntity.ok(orderService.newOrder(orderId));
    }

}
