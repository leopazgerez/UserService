package com.example.userservice.Utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RabbitValues {

    @Value("${rabbitmq.queue.user.name}")
    private String registeredUserQueue;


    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.user.key}")
    private String registeredUserRoutingKey;


    public String getRegisteredUserQueue() {

        return registeredUserQueue;
    }

    public String getExchange() {
        return exchange;
    }

    public String getRegisteredUserRoutingKey() {
        return registeredUserRoutingKey;
    }
}
