package com.backend.OnlineStore.service;

import com.backend.OnlineStore.entity.*;
import com.backend.OnlineStore.exceptions.OrderException;
import com.backend.OnlineStore.exceptions.ResourceNotFoundException;
import com.backend.OnlineStore.model.OrderDTO;
import com.backend.OnlineStore.model.OrderLineDTO;
import com.backend.OnlineStore.repository.OrderRepository;
import com.backend.OnlineStore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderLineService orderLineService;


    @Autowired
    public OrderService(OrderRepository orderRepository, ProductRepository productRepository, OrderLineService orderLineService) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;


        this.orderLineService = orderLineService;
    }

    public OrderDTO toDTO(Order order) {
        if (order == null) {
            return null;
        }

        List<OrderLineDTO> orderLineDTOs = order.getOrderLines()
                .stream()
                .map(orderLineService::toOrderLineDto)
                .collect(Collectors.toList());

        return new OrderDTO(
                order.getUser() != null ? order.getUser().getEmail() : null, // ose merrni emrin e përdoruesit
                order.getTotalCost(),
                order.getDeliveryAddress(),
                order.getOrderDate(),
                order.getStatus().name(),
                orderLineDTOs
        );
    }

    // Metoda për të konvertuar OrderDTO në Order
    public Order toEntity(OrderDTO orderDTO, User user) {
        if (orderDTO == null) {
            return null;
        }

        Order order = new Order();
        order.setUser(user); // Shtoni përdoruesin
        order.setTotalCost(orderDTO.getTotalCost());
        order.setDeliveryAddress(orderDTO.getDeliveryAddress());
        order.setOrderDate(orderDTO.getOrderDate());
        order.setStatus(OrderStatus.valueOf(orderDTO.getStatus())); // Konvertohet në enum

        Set<OrderLine> orderLines = orderDTO.getOrderLines()
                .stream()
                .map(orderLineService::toOrderLineEntity)
                .collect(Collectors.toSet());

        order.setOrderLines(orderLines);
        return order;
    }


    public OrderDTO createOrder(OrderDTO orderDTO, User user) {
        // Konvertoni OrderDTO në Order dhe lidheni me përdoruesin
        Order order = toEntity(orderDTO, user);

        for (OrderLineDTO lineDTO : orderDTO.getOrderLines()) {
            Product product = productRepository.findById(lineDTO.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + lineDTO.getProductId()));

            OrderLine orderLine = new OrderLine();
            orderLine.setProduct(product);
            orderLine.setQuantity(lineDTO.getQuantity());
            orderLine.setProductPrice(product.getPrice()); // ose çmimi që keni

            order.setOrderLines((Set<OrderLine>) orderLine);
        }

        // Ruajmë porosinë në bazën e të dhënave
        Order savedOrder = orderRepository.save(order);
        return toDTO(savedOrder);
    }

    public Optional<List<OrderDTO>> findOrdersByUser(Long userId) {
        return Optional.ofNullable(orderRepository.findByUserId(userId)
                .map(orders -> orders.stream()
                        .map(this::toDTO)
                        .toList())
                .orElseThrow(() -> new ResourceNotFoundException("Order not found")));
    }

    public Optional<List<OrderDTO>> findOrdersByStatus(OrderStatus status) {
        return Optional.ofNullable(orderRepository.findByStatus(status)
                .map(orders -> orders.stream()
                        .map(this::toDTO)
                        .toList())
                .orElseThrow(() -> new ResourceNotFoundException("Order not found")));
    }

    public Optional<OrderDTO> findOrderById(Long id) {
        return Optional.ofNullable(orderRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found")));
    }


    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}