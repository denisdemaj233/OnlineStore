package com.backend.OnlineStore.model;

import lombok.Data;

import java.util.List;

@Data
public class CartDTO {
    private List<OrderLineDTO> orderLines;

    public double getTotalCost() {
        return orderLines.stream().mapToDouble(line -> line.getProductPrice() * line.getQuantity()).sum();
    }

}
