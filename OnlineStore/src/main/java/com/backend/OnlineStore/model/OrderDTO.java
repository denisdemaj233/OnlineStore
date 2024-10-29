package com.backend.OnlineStore.model;

import lombok.*;

import java.util.List;
import java.util.Date;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

    private String userName;
    private double totalCost;
    private String deliveryAddress;
    private Date orderDate;
    private String status;
    private List<OrderLineDTO> orderLines;


}

