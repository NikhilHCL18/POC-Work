package com.hcl.driver.controller;

import com.hcl.order.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@RestController
@RequestMapping("driver/api")
public class DriverRestApi {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping(value = "/orderDetailAndAddressDetailById/{id}")
    public String getOrderDetailAndAddressDetailsById(@PathVariable(value = "id") Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        return restTemplate.
                exchange("http://localhost:8083/order/api/getOrderById/" + id, HttpMethod.GET, entity, String.class).getBody();
    }

    @PutMapping(value = "/orderPickedUp")
    public String updateOrderStatusAsPickedUp(@RequestBody Order order){

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Order> entity = new HttpEntity<Order>(order, headers);

        order = restTemplate.exchange(
                "http://localhost:8083/order/api/updateOrderStatus", HttpMethod.PUT, entity, Order.class).getBody();

        String result= "The Order Status is updated to PICKED-UP for the orderId::"+order.getId();
        return result;
    }

    @PutMapping(value = "/orderDelivered")
    public String updateOrderStatusAsDelivered(@RequestBody Order order){

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Order> entity = new HttpEntity<Order>(order, headers);

        order = restTemplate.exchange(
                "http://localhost:8083/order/api/updateOrderStatus", HttpMethod.PUT, entity, Order.class).getBody();

        String result= "The Order Status is updated to DELIVERED for the orderId::"+order.getId();
        return result;
    }
}
