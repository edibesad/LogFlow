package com.logflow.orderservice.service;

import java.util.List;

import com.logflow.orderservice.entity.Order;

public interface OrderService {
    List<Order> getOrders();
}
