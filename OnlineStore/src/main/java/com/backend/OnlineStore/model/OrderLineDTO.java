package com.backend.OnlineStore.model;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderLineDTO {

    private String productName;
    private int quantity;
    private double productPrice;

}

