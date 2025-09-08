package com.fooddelivery;

import org.springframework.stereotype.Component;

@Component
public class DummyRedisClient implements RestaurantStatusCache{

    @Override
    public boolean isActive() {
        return true; // restaurant is accepting orders
    }
}
