package com.backend.OnlineStore.service;

import com.backend.OnlineStore.entity.OrderLine;
import com.backend.OnlineStore.exceptions.ResourceNotFoundException;
import com.backend.OnlineStore.model.OrderLineDTO;
import com.backend.OnlineStore.repository.OrderLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class OrderLineService {

    private final OrderLineRepository orderLineRepository;


    @Autowired
    public OrderLineService(OrderLineRepository orderLineRepository) {
        this.orderLineRepository = orderLineRepository;


    }
    public OrderLineDTO toOrderLineDto(OrderLine orderLine) {
        if (orderLine == null) {
            return null;
        }

        return new OrderLineDTO(
                orderLine.getOrder().getId(),
                orderLine.getProduct() != null ? orderLine.getProduct().getTitle() : null,
                orderLine.getQuantity(),
                orderLine.getProductPrice()
        );
    }

    // Metoda për të konvertuar OrderLineDTO në OrderLine
    public OrderLine toOrderLineEntity(OrderLineDTO orderLineDTO) {
        if (orderLineDTO == null) {
            return null;
        }
        OrderLine orderLine = new OrderLine();
        orderLine.setQuantity(orderLineDTO.getQuantity());
        orderLine.setProductPrice(orderLineDTO.getProductPrice());
        return orderLine;
    }
    public OrderLineDTO saveOrderLine(OrderLineDTO orderLineDTO) {

        OrderLine orderLine = toOrderLineEntity(orderLineDTO);
        OrderLine savedOrderLine = orderLineRepository.save(orderLine);
        return toOrderLineDto(savedOrderLine);
    }

    public Optional<List<OrderLineDTO>> findOrderLinesByOrder(Long orderId) {
        return Optional.ofNullable(orderLineRepository.findByOrderId(orderId)
                .map(orderLines -> orderLines.stream()
                        .map(this::toOrderLineDto)
                        .toList())
                .orElseThrow(() -> new ResourceNotFoundException("ORDELINES with id " + orderId + " not found")));
    }


    public void deleteOrderLine(Long id) {
        orderLineRepository.deleteById(id);
    }
}
