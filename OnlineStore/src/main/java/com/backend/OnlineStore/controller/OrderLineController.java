package com.backend.OnlineStore.controller;

import com.backend.OnlineStore.entity.OrderLine;
import com.backend.OnlineStore.model.OrderLineDTO;
import com.backend.OnlineStore.service.OrderLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orderlines")
public class OrderLineController {

    @Autowired
    private OrderLineService orderLineService;


    @PostMapping
    public OrderLineDTO createOrderLine(@RequestBody OrderLineDTO orderLineDTO) {
        return orderLineService.saveOrderLine(orderLineDTO);
    }


    @GetMapping("/order/{orderId}")
    public Optional<List<OrderLineDTO>> getOrderLinesByOrder(@PathVariable Long orderId) {
        return orderLineService.findOrderLinesByOrder(orderId);
    }


    @DeleteMapping("/{id}")
    public void deleteOrderLine(@PathVariable Long id) {
        orderLineService.deleteOrderLine(id);
    }
}
