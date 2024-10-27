package com.backend.OnlineStore.entity;

import lombok.Data;

import java.util.List;

@Data
public class Cart {

    private List<OrderLine> orderLines;

    public double getTotalCost() {
        return orderLines.stream().mapToDouble(line -> line.getProductPrice() * line.getQuantity()).sum();
    }

}
