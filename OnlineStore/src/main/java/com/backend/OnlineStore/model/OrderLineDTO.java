package com.backend.OnlineStore.model;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderLineDTO {

    private Long id;
    private Long orderId;
    private Long productId;
    private int quantity;


}

