package com.backend.OnlineStore.service;

import com.backend.OnlineStore.entity.Order;
import com.backend.OnlineStore.entity.OrderLine;
import com.backend.OnlineStore.entity.OrderStatus;
import com.backend.OnlineStore.entity.Product;
import com.backend.OnlineStore.exceptions.OrderException;
import com.backend.OnlineStore.exceptions.ResourceNotFoundException;
import com.backend.OnlineStore.model.OrderDTO;
import com.backend.OnlineStore.model.mappers.OrderMapper;
import com.backend.OnlineStore.model.mappers.UserMapper;
import com.backend.OnlineStore.repository.OrderRepository;
import com.backend.OnlineStore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderMapper orderMapper;


    public OrderService(OrderRepository orderRepository, ProductRepository productRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;

        this.orderMapper = orderMapper;
    }

    public OrderDTO createOrder(OrderDTO orderDTO) {

        Order order = orderMapper.toEntity(orderDTO);

        for (OrderLine line : order.getOrderLines()) {
            Product product = productRepository.findById(line.getProduct().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product with id " + line.getProduct().getId() + " not found"));
            if (product.getAvailability() < line.getQuantity()) {
                throw new OrderException("Not enough stock for product: " + product.getTitle());
            }
        }


        Order savedOrder = orderRepository.save(order);
        return orderMapper.toDTO(savedOrder);
    }

    public Optional<List<OrderDTO>> findOrdersByUser(Long userId) {
        return Optional.ofNullable(orderRepository.findByUserId(userId)
                .map(orders -> orders.stream()
                        .map(orderMapper::toDTO)
                        .toList())
                .orElseThrow(() -> new ResourceNotFoundException("Order not found")));
    }

    public Optional<List<OrderDTO>> findOrdersByStatus(OrderStatus status) {
        return Optional.ofNullable(orderRepository.findByStatus(status)
                .map(orders -> orders.stream()
                        .map(orderMapper::toDTO)
                        .toList())
                .orElseThrow(() -> new ResourceNotFoundException("Order not found")));
    }

    public Optional<OrderDTO> findOrderById(Long id) {
        return Optional.ofNullable(orderRepository.findById(id)
                .map(orderMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found")));
    }


    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}