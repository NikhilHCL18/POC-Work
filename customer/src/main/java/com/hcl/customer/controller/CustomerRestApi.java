package com.hcl.customer.controller;


import com.hcl.order.model.ItemQuantity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import com.hcl.order.model.Order;

import java.util.Arrays;


@RestController
@RequestMapping("customer/api")
public class CustomerRestApi {

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value = "/createOrder", method = RequestMethod.POST)
    public String createOrderByCustomer(@RequestBody Order order) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Order> entity = new HttpEntity<Order>(order, headers);

        return restTemplate.exchange(
                "http://localhost:8083/order/api/createOrder", HttpMethod.POST, entity, String.class).getBody();
    }


    @GetMapping(value = "/orderDetailById/{id}")
    public String getOrderDetailById(@PathVariable(value = "id") Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        return restTemplate.
                exchange("http://localhost:8083/order/api/getOrderById/" + id, HttpMethod.GET, entity, String.class).getBody();
    }

    @GetMapping(value = "/getTotalFare/{id}")
    public String getTotalFareAndEstimatedTimeToDeliver(@PathVariable(value = "id") Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        Order order = restTemplate.
                exchange("http://localhost:8083/order/api/getOrderById/" + id, HttpMethod.GET, entity, Order.class).getBody();

        int totalFare = 0;
        int perItemCost = 0;
        for (ItemQuantity itemQuantity : order.getItems()) {
            perItemCost += perItemCost;
            perItemCost = itemQuantity.getPrice() * itemQuantity.getQuantity();
            System.out.println("PerItemCOst::" + perItemCost);
        }
        int baseTaxes = (5 * perItemCost) / 100;
        int deliveryCharge = 1 * 25;
        totalFare = perItemCost + baseTaxes + deliveryCharge;

        long foodPreparationTime = order.getUnitPreparationTime() * order.getItems().size();
        long deliveryTime = order.getDeliveryTime();

        String result = "Total fare::" + totalFare +
                " Food Preparation Time::" + foodPreparationTime + " Delivery Time::" + deliveryTime;
        return result;
    }

    @PutMapping(value = "/cancelOrder")
    public String cancelOrder(@RequestBody Order order){

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Order> entity = new HttpEntity<Order>(order, headers);

         order = restTemplate.exchange(
                "http://localhost:8083/order/api/updateOrderStatus", HttpMethod.PUT, entity, Order.class).getBody();

         String result= "The Order Status is updated to CANCELLED for the orderId::"+order.getId();
         return result;
    }

}