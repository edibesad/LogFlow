package com.logflow.orderservice.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.logflow.orderservice.entity.Order;
import com.logflow.orderservice.service.OrderService;

public class OrderServiceImpl implements OrderService {

    @Override
    public List<Order> getOrders() {
        return new ArrayList<Order>() {
        };
    }

}
