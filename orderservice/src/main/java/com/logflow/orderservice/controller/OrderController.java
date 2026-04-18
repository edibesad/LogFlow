package com.logflow.orderservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.logflow.orderservice.kafka.OrderLogProducer;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class OrderController {

    OrderLogProducer orderLogProducer;

    OrderController(OrderLogProducer orderLogProducer) {
        this.orderLogProducer = orderLogProducer;
    }

    @GetMapping(value = "/orders")
    public ResponseEntity<List<String>> getOrders() {
        List<String> orders = new ArrayList<>();
        orders.add("Order1");
        orders.add("Order2");

        orderLogProducer.sendLog("Succesfully got orders");

        return ResponseEntity.ok(orders);
    }
}
