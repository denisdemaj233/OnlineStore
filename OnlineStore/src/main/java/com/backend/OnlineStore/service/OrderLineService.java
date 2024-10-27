package com.backend.OnlineStore.service;

import com.backend.OnlineStore.entity.OrderLine;
import com.backend.OnlineStore.model.OrderLineDTO;
import com.backend.OnlineStore.model.mappers.OrderLineMapper;
import com.backend.OnlineStore.repository.OrderLineRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderLineService {

    private final OrderLineRepository orderLineRepository;

    public OrderLineService(OrderLineRepository orderLineRepository) {
        this.orderLineRepository = orderLineRepository;
    }

    public OrderLineDTO saveOrderLine(OrderLineDTO orderLineDTO) {

        OrderLine orderLine = OrderLineMapper.INSTANCE.orderLineDTOToOrderLine(orderLineDTO);
        OrderLine savedOrderLine = orderLineRepository.save(orderLine);
        return OrderLineMapper.INSTANCE.orderLineToOrderLineDTO(savedOrderLine);
    }

    public Optional<List<OrderLineDTO>> findOrderLinesByOrder(Long orderId) {
        return orderLineRepository.findByOrderId(orderId)
                .map(orderLines -> orderLines.stream()
                        .map(OrderLineMapper.INSTANCE::orderLineToOrderLineDTO)
                        .toList());
    }


    public void deleteOrderLine(Long id) {
        orderLineRepository.deleteById(id);
    }
}
