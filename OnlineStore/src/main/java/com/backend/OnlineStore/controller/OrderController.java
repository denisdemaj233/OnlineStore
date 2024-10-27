package com.backend.OnlineStore.controller;
import com.backend.OnlineStore.entity.Order;
import com.backend.OnlineStore.model.OrderDTO;
import com.backend.OnlineStore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) {
        OrderDTO savedOrder = orderService.createOrder(orderDTO);
        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }


    @GetMapping("/user/{userId}")
    public Optional<List<OrderDTO>> getOrdersByUser(@PathVariable Long userId) {
        return orderService.findOrdersByUser(userId);
    }


    @GetMapping("/{id}")
    public Optional<OrderDTO> getOrderById(@PathVariable Long id) {
        return orderService.findOrderById(id);
    }


    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }
}
