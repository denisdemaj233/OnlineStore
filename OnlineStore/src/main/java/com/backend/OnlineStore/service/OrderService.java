package com.backend.OnlineStore.service;

import com.backend.OnlineStore.entity.Order;
import com.backend.OnlineStore.entity.OrderLine;
import com.backend.OnlineStore.entity.OrderStatus;
import com.backend.OnlineStore.entity.Product;
import com.backend.OnlineStore.exceptions.OrderException;
import com.backend.OnlineStore.exceptions.ResourceNotFoundException;
import com.backend.OnlineStore.repository.OrderRepository;
import com.backend.OnlineStore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;


    public Order createOrder(Order order) {

        for (OrderLine line : order.getOrderLines()) {
            Product product = productRepository.findById(line.getProduct().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product with id " + line.getProduct().getId() + " not found"));
            if (product.getAvailability() < line.getQuantity()) {
                throw new OrderException("Not enough stock for product: " + product.getTitle());
            }
        }
        return orderRepository.save(order);
    }

    public  Optional<List<Order>> findOrdersByUser(Long userId) {
        return orderRepository.findByUserId(userId);
    }


    public  Optional<List<Order>> findOrdersByStatus(OrderStatus status) {
        return orderRepository.findByStatus(status);
    }


    public Optional<Order> findOrderById(Long id) {
        return orderRepository.findById(id);
    }


    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
