package com.fooddelivery;

public class DummyKafkaConsumer implements MessageConsumer {

    private final DeliveryService deliveryService;

    public DummyKafkaConsumer(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @Override
    public String consumeNewDeliveryRequest() {
        deliveryService.assignNewRider("location: area XYZ");
    }
}
