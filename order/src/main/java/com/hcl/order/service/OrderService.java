package com.hcl.order.service;

import com.hcl.order.model.Order;

import java.util.List;

public interface OrderService {

    Order createOrder(Order order);
    List<Order> getOrderDetails();
}
