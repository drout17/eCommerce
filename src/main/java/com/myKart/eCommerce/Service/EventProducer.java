package com.myKart.eCommerce.Service;

import com.google.gson.Gson;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Service
public class EventProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public EventProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishEvent(String eventType, String orderId) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("eventType", eventType);
        payload.put("orderId", orderId);
        payload.put("timestamp", Instant.now().toString());

        kafkaTemplate.send("ecommerce-events", new Gson().toJson(payload));
        System.out.println("📤 Published: " + eventType + " for order " + orderId);
    }
}

