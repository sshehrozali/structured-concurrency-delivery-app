package com.fooddelivery;

import org.springframework.stereotype.Component;

@Component
public class PostgresDB implements DatabaseClient{

    @Override
    public void save() {
        // save record
    }
}
