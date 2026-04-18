package com.logflow.orderservice.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderLogProducer {
    final KafkaTemplate<String, String> kafkaTemplate;

    OrderLogProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendLog(String message) {
        kafkaTemplate.send("order-logs", message);
    }
}
