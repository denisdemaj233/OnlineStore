package com.backend.OnlineStore.controller;

import com.backend.OnlineStore.entity.OrderLine;
import com.backend.OnlineStore.model.OrderLineDTO;
import com.backend.OnlineStore.service.OrderLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/order-lines")
public class OrderLineController {

    private final OrderLineService orderLineService;

    @Autowired
    public OrderLineController(OrderLineService orderLineService) {
        this.orderLineService = orderLineService;
    }

    @PostMapping
    public ResponseEntity<OrderLineDTO> createOrderLine(@RequestBody OrderLineDTO orderLineModel) {
        OrderLine orderLine = orderLineService.toEntity(orderLineModel);
        OrderLine savedOrderLine = orderLineService.save(orderLine);
        return ResponseEntity.ok(orderLineService.toDTO(savedOrderLine));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderLineDTO> getOrderLineById(@PathVariable Long id) {
        OrderLine orderLine = orderLineService.findById(id);
        return ResponseEntity.ok(orderLineService.toDTO(orderLine));
    }

    @GetMapping
    public ResponseEntity<List<OrderLineDTO>> getAllOrderLines() {
        List<OrderLine> orderLines = orderLineService.findAll();
        List<OrderLineDTO> orderLineModels = orderLines.stream()
                .map(orderLineService::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(orderLineModels);
    }


//    @PutMapping("/{id}")
//    public ResponseEntity<OrderLineDTO> updateOrderLine(@PathVariable Long id, @RequestBody OrderLineDTO orderLineModel) {
//        OrderLine updatedOrderLine = orderLineService.update(id, orderLineModel);
//        return ResponseEntity.ok(orderLineService.toDTO(updatedOrderLine));
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteOrderLine(@PathVariable Long id) {
//        orderLineService.delete(id);
//        return ResponseEntity.noContent().build();
//    }
}
