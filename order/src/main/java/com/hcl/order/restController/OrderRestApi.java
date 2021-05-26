package com.hcl.order.restController;

import com.hcl.order.model.Order;
import com.hcl.order.service.OrderService;
import com.hcl.order.util.GeneratePdfOrderInvoice;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class OrderRestApi {
    @Autowired
    OrderService orderService;

    @Autowired
    public OrderRestApi(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/restaurants/{rid}/orders")
    public Order createOder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    @GetMapping(("/restaurants/orders"))
    public List<Order> getOrderDetails(){
        System.out.println("Inside the get method of getOrderDetails");
        return orderService.getOrderDetails();

    }

    @GetMapping(("/restaurants/orders/{id}"))
    public Order getOrderDetailsById(@PathVariable(value="id") Long id){
        System.out.println("Inside the get method of getOrderDetailsById");
        String orderId=id.toString();
        Optional<Order> order=orderService.getOrderById(orderId);
        return order.get();

    }
    @RequestMapping(value = "/restaurants/orders/{id}/orderInvoicePdf", method = RequestMethod.GET)
    public ResponseEntity<String> orderInvoicePdf(@PathVariable(value="id") Long id) {
        try {
            String orderId=id.toString();
            Optional<Order> order = orderService.getOrderById(orderId);
            GeneratePdfOrderInvoice.OrderReport(order.get());
        }catch (DocumentException | IOException e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Report has been generated ", HttpStatus.OK);
    }
}
