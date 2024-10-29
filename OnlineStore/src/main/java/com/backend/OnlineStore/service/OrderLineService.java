package com.backend.OnlineStore.service;

import com.backend.OnlineStore.entity.Order;
import com.backend.OnlineStore.entity.OrderLine;
import com.backend.OnlineStore.entity.Product;
import com.backend.OnlineStore.exceptions.ResourceNotFoundException;
import com.backend.OnlineStore.model.OrderLineDTO;
import com.backend.OnlineStore.repository.OrderLineRepository;
import com.backend.OnlineStore.repository.OrderRepository;
import com.backend.OnlineStore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderLineService {

    private final OrderLineRepository orderLineRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Autowired
    public OrderLineService(OrderLineRepository orderLineRepository,
                            OrderRepository orderRepository,
                            ProductRepository productRepository) {
        this.orderLineRepository = orderLineRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    // Convert OrderLine to OrderLineModel
    public OrderLineDTO toDTO(OrderLine orderLine) {
        if (orderLine == null) {
            return null;
        }

        return new OrderLineDTO(
                orderLine.getOrder() != null ? orderLine.getOrder().getId() : null,
                orderLine.getProduct() != null ? orderLine.getProduct().getId() : null,
                orderLine.getQuantity()
        );
    }

    // Convert OrderLineModel to OrderLine entity
    public OrderLine toEntity(OrderLineDTO orderLineModel) {
        if (orderLineModel == null) {
            return null;
        }


        Product product = productRepository.findById(orderLineModel.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        OrderLine orderLine = new OrderLine();

        orderLine.setProduct(product);
        orderLine.setQuantity(orderLineModel.getQuantity());
        orderLine.setProductPrice(product.getPrice());
        orderLine.setOrder(orderRepository.getOne(orderLineModel.getOrderId()));


        return orderLine;
    }

    public List<OrderLineDTO> toDTOList(List<OrderLine> orderLines) {
        if (orderLines == null) {
            return null;
        }

        return orderLines.stream()
                .map(this::toDTO) // Përdor metodën e konvertimit të individëve
                .collect(Collectors.toList());
    }

    public List<OrderLine> toEntityList(List<OrderLineDTO> orderLineDTOs) {
        if (orderLineDTOs == null) {
            return null;
        }

        return orderLineDTOs.stream()
                .map(this::toEntity) // Përdor metodën e konvertimit të individëve
                .collect(Collectors.toList());
    }

    // Create OrderLine
    public OrderLine save(OrderLine orderLine) {
        return orderLineRepository.save(orderLine);
    }

    // Read OrderLine by ID
    public OrderLine findById(Long id) {
        return orderLineRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("OrderLine not found"));
    }

    // Read all OrderLines
    public List<OrderLine> findAll() {
        return orderLineRepository.findAll();
    }

}