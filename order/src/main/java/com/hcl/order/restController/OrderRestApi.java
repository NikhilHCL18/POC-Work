package com.hcl.order.restController;

import com.hcl.order.model.Order;
import com.hcl.order.service.OrderService;
import com.hcl.order.util.GeneratePdfOrderInvoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderRestApi {
    @Autowired
    OrderService orderService;

    @Autowired
    public OrderRestApi(OrderService orderService) {
        this.orderService = orderService;
    }

    /*@RequestMapping(value = "/restaurants/{rid}/orders", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)*/
    @PostMapping("/restaurants/{rid}/orders")
    public Order createOder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    @RequestMapping(value = "/orderInvoicePdf", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> orderInvoicePdf() {

        List<Order> Orders = orderService.getOrderDetails();

        ByteArrayInputStream bis = GeneratePdfOrderInvoice.OrderReport(Orders);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=OrderInvoice.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
}
