package com.backend.OnlineStore.controller;

import com.backend.OnlineStore.entity.OrderLine;
import com.backend.OnlineStore.service.OrderLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orderlines")
public class OrderLineController {

    @Autowired
    private OrderLineService orderLineService;


    @PostMapping
    public OrderLine createOrderLine(@RequestBody OrderLine orderLine) {
        return orderLineService.saveOrderLine(orderLine);
    }

    // Get order lines by order ID
    @GetMapping("/order/{orderId}")
    public List<OrderLine> getOrderLinesByOrder(@PathVariable Long orderId) {
        return orderLineService.findOrderLinesByOrder(orderId);
    }


    @DeleteMapping("/{id}")
    public void deleteOrderLine(@PathVariable Long id) {
        orderLineService.deleteOrderLine(id);
    }
}
