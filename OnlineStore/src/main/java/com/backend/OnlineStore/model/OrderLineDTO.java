package com.backend.OnlineStore.model;

import lombok.Data;

@Data
public class OrderLineDTO {
    private Long id;
    private Long productId;
    private String productName;
    private int quantity;
    private double productPrice;

}

