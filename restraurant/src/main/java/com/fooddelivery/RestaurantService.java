package com.fooddelivery;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.concurrent.StructuredTaskScope;

@Service
public class RestaurantService {

    private final DatabaseClient databaseClient;
    private final RestaurantStatusCache restaurantStatusCache;
    private final RestClient restClient;

    public RestaurantService(DatabaseClient databaseClient, RestaurantStatusCache restaurantStatusCache) {
        this.databaseClient = databaseClient;
        this.restaurantStatusCache = restaurantStatusCache;
        this.restClient = RestClient.builder()
                .baseUrl("notification-service/api/v1")
                .build();
    }

    public Boolean assignOrder(String orderId) throws InterruptedException {
        if (!restaurantStatusCache.isActive()) {
            throw new RuntimeException("restaurant is busy or not accepting new orders");
        }

        var scope = StructuredTaskScope.open();

        // Blocking IO -> VTs non-blocking
        var dbTask = scope.fork(databaseClient::save);
        var notifyTask = scope.fork(this::callNotificationService);

        scope.join();
        return true;
    }

    private void callNotificationService() {
        restClient.post()
                .uri("/notify/restaurant")
                .contentType(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(Void.class);
    }
}
