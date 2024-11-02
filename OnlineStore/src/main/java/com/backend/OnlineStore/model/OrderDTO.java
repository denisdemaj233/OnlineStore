package com.backend.OnlineStore.model;

import com.backend.OnlineStore.entity.OrderStatus;
import lombok.*;

import java.util.List;
import java.util.Date;
import java.util.Set;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {


    private Long id;
    private Long userId;
    private String email;
    private double totalCost;
    private String deliveryAddress;
    private Date orderDate;
    private OrderStatus status;
    private List<OrderLineDTO> orderLines;


}

