package com.backend.OnlineStore.controller;

import com.backend.OnlineStore.entity.Order;
import com.backend.OnlineStore.model.OrderDTO;
import com.backend.OnlineStore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderModel) {
        Order order = orderService.toEntity(orderModel);
        Order savedOrder = orderService.save(order);
        return ResponseEntity.ok(orderService.toDTO(savedOrder));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long id) {
        Order order = orderService.findById(id);
        return ResponseEntity.ok(orderService.toDTO(order));
    }

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        List<Order> orders = orderService.findAll();
        List<OrderDTO> orderModels = orders.stream()
                .map(orderService::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(orderModels);
    }


    @PutMapping("/{id}")
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable Long id, @RequestBody OrderDTO orderModel) {
        Order updatedOrder = orderService.update(id, orderModel);
        return ResponseEntity.ok(orderService.toDTO(updatedOrder));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
