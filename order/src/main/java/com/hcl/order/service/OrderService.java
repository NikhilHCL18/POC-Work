package com.hcl.order.service;

import com.hcl.order.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    Order createOrder(Order order);
    List<Order> getOrderDetails();
    Optional<Order> getOrderById(String id);
}
