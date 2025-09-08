package com.fooddelivery;

import org.springframework.stereotype.Component;

@Component
public class DummyRedisCache implements RidersLookupCache{

    @Override
    public String searchNearbyRider(String location) {
        return "rider1 found";
    }
}
