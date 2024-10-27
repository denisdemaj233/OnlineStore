package com.backend.OnlineStore.service;

import com.backend.OnlineStore.entity.OrderLine;
import com.backend.OnlineStore.repository.OrderLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderLineService {

    @Autowired
    private OrderLineRepository orderLineRepository;


    public OrderLine saveOrderLine(OrderLine orderLine) {
        return orderLineRepository.save(orderLine);
    }

    public List<OrderLine> findOrderLinesByOrder(Long orderId) {
        return orderLineRepository.findByOrderId(orderId);
    }

    public void deleteOrderLine(Long id) {
        orderLineRepository.deleteById(id);
    }
}
