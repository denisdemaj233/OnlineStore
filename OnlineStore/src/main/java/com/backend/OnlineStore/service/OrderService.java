package com.backend.OnlineStore.service;

import com.backend.OnlineStore.entity.Order;
import com.backend.OnlineStore.entity.OrderStatus;
import com.backend.OnlineStore.entity.User;
import com.backend.OnlineStore.exceptions.ResourceNotFoundException;
import com.backend.OnlineStore.model.OrderDTO;
import com.backend.OnlineStore.model.OrderLineDTO;
import com.backend.OnlineStore.repository.OrderRepository;
import com.backend.OnlineStore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final OrderLineService orderLineService;

    @Autowired
    public OrderService(OrderRepository orderRepository, UserRepository userRepository, OrderLineService orderLineService) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.orderLineService = orderLineService;
    }

    public OrderDTO toDTO(Order order) {
        if (order == null) {
            return null;
        }



        return new OrderDTO(
                order.getUser() != null ? order.getUser().getId() : null,
                order.getTotalCost(),
                order.getDeliveryAddress(),
                order.getOrderDate(),
                order.getStatus(),
                orderLineService.toDTOList(order.getOrderLines())
        );
    }

    // Convert OrderModel to Order entity
    public Order toEntity(OrderDTO orderModel) {
        if (orderModel == null) {
            return null;
        }

        User user = userRepository.findById(orderModel.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Order order = new Order();
        order.setUser(user);
        order.setTotalCost(orderModel.getTotalCost());
        order.setDeliveryAddress(orderModel.getDeliveryAddress());
        order.setOrderDate(orderModel.getOrderDate());
        order.setStatus(orderModel.getStatus());
        order.setOrderLines(orderLineService.toEntityList(orderModel.getOrderLines()));

        return order;
    }



    // Create Order
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    // Read Order by ID
    public Order findById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
    }

    // Read all Orders
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    // Update Order
    public Order update(Long id, OrderDTO orderModel) {
        Order existingOrder = findById(id);
        existingOrder.setTotalCost(orderModel.getTotalCost());
        existingOrder.setDeliveryAddress(orderModel.getDeliveryAddress());
        existingOrder.setOrderDate(orderModel.getOrderDate());
        existingOrder.setStatus(orderModel.getStatus());
        return orderRepository.save(existingOrder);
    }

    // Delete Order
    public void delete(Long id) {
        Order existingOrder = findById(id);
        orderRepository.delete(existingOrder);
    }
}