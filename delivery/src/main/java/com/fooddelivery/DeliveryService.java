package com.fooddelivery;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.concurrent.StructuredTaskScope;

@Service
public class DeliveryService {

    private final RidersLookupCache ridersLookupCache;
    private final DatabaseClient databaseClient;
    private final RestClient restClient;

    public DeliveryService(RidersLookupCache ridersLookupCache, DatabaseClient databaseClient) {
        this.ridersLookupCache = ridersLookupCache;
        this.databaseClient = databaseClient;
        this.restClient = RestClient.builder()
                .baseUrl("notification-service/api/v1")
                .build();
    }

    public void assignNewRider(String location) throws InterruptedException {
        var scope = StructuredTaskScope.open();

        var rider = scope.fork(() -> databaseClient.save());
        scope.fork(() -> ridersLookupCache.searchNearbyRider(location));

        scope.join();

        // once rider is found, notify rider
        scope.fork(this::callNotificationService);
    }

    private void callNotificationService() {
        restClient.post()
                .uri("/notify/rider")
                .contentType(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(Void.class);
    }
}
