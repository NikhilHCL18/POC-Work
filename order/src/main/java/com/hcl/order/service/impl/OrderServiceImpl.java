package com.hcl.order.service.impl;

import com.hcl.order.model.Order;
import com.hcl.order.repository.OrderRepository;
import com.hcl.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order createOrder(Order order) {
        order.setOrderTime(System.currentTimeMillis());
        order.setTotalPrice(order.getItems().stream().mapToInt(e -> e.getPrice() * e.getQuantity()).sum());
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getOrderDetails() {
        return (List<Order>) orderRepository.findAll();
    }

    public Order getOrderById(String id) {
        System.out.println("ID:::"+id);
        return orderRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException
                ("Order is not found for this orderId::"+id));
    }

    @Override
    public void removeOrderDetails(String id) {
        orderRepository.deleteById(id);
    }


}
