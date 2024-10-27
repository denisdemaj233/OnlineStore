package com.backend.OnlineStore.model;

import lombok.Data;

@Data
public class OrderLineDTO {

    private String productName;
    private int quantity;
    private double productPrice;

}

