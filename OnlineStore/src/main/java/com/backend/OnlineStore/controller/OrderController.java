package com.backend.OnlineStore.controller;
import com.backend.OnlineStore.entity.Order;
import com.backend.OnlineStore.entity.User;
import com.backend.OnlineStore.exceptions.ResourceNotFoundException;
import com.backend.OnlineStore.model.OrderDTO;
import com.backend.OnlineStore.repository.UserRepository;
import com.backend.OnlineStore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) {
        User user = userRepository.findByEmail(orderDTO.getUserName())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        // Krijoni porosinë
        OrderDTO savedOrder = orderService.createOrder(orderDTO, user);
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
