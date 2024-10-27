package com.backend.OnlineStore.service;

import com.backend.OnlineStore.entity.Order;
import com.backend.OnlineStore.entity.OrderLine;
import com.backend.OnlineStore.entity.OrderStatus;
import com.backend.OnlineStore.entity.Product;
import com.backend.OnlineStore.exceptions.OrderException;
import com.backend.OnlineStore.exceptions.ResourceNotFoundException;
import com.backend.OnlineStore.model.OrderDTO;
import com.backend.OnlineStore.model.mappers.OrderMapper;
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

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public OrderDTO createOrder(OrderDTO orderDTO) {
        Order order = OrderMapper.INSTANCE.orderDTOToOrder(orderDTO);

        for (OrderLine line : order.getOrderLines()) {
            Product product = productRepository.findById(line.getProduct().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product with id " + line.getProduct().getId() + " not found"));
            if (product.getAvailability() < line.getQuantity()) {
                throw new OrderException("Not enough stock for product: " + product.getTitle());
            }
        }


        Order savedOrder = orderRepository.save(order);

        return OrderMapper.INSTANCE.orderToOrderDTO(savedOrder);
    }

    public Optional<List<OrderDTO>> findOrdersByUser(Long userId) {
        return orderRepository.findByUserId(userId)
                .map(orders -> orders.stream()
                        .map(OrderMapper.INSTANCE::orderToOrderDTO)
                        .toList());
    }

    public Optional<List<OrderDTO>> findOrdersByStatus(OrderStatus status) {
        return orderRepository.findByStatus(status)
                .map(orders -> orders.stream()
                        .map(OrderMapper.INSTANCE::orderToOrderDTO)
                        .toList());
    }

    public Optional<OrderDTO> findOrderById(Long id) {
        return orderRepository.findById(id)
                .map(OrderMapper.INSTANCE::orderToOrderDTO);
    }


    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}