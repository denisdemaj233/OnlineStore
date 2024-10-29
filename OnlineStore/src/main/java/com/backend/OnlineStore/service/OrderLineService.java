package com.backend.OnlineStore.service;

import com.backend.OnlineStore.entity.OrderLine;
import com.backend.OnlineStore.exceptions.ResourceNotFoundException;
import com.backend.OnlineStore.model.OrderLineDTO;
import com.backend.OnlineStore.model.mappers.OrderLineMapper;
import com.backend.OnlineStore.model.mappers.UserMapper;
import com.backend.OnlineStore.repository.OrderLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class OrderLineService {

    private final OrderLineRepository orderLineRepository;
    private final OrderLineMapper orderLineMapper;

    @Autowired
    public OrderLineService(OrderLineRepository orderLineRepository,OrderLineMapper orderLineMapper) {
        this.orderLineRepository = orderLineRepository;
        this.orderLineMapper = orderLineMapper;

    }

    public OrderLineDTO saveOrderLine(OrderLineDTO orderLineDTO) {

        OrderLine orderLine = orderLineMapper.toEntity(orderLineDTO);
        OrderLine savedOrderLine = orderLineRepository.save(orderLine);
        return orderLineMapper.toDTO(savedOrderLine);
    }

    public Optional<List<OrderLineDTO>> findOrderLinesByOrder(Long orderId) {
        return Optional.ofNullable(orderLineRepository.findByOrderId(orderId)
                .map(orderLines -> orderLines.stream()
                        .map(orderLineMapper::toDTO)
                        .toList())
                .orElseThrow(() -> new ResourceNotFoundException("ORDELINES with id " + orderId + " not found")));
    }


    public void deleteOrderLine(Long id) {
        orderLineRepository.deleteById(id);
    }
}
