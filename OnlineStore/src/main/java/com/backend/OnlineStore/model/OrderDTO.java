package com.backend.OnlineStore.model;

import lombok.Data;

import java.util.List;
import java.util.Date;

@Data
public class OrderDTO {
    private Long id;
    private String userName;
    private double totalCost;
    private String deliveryAddress;
    private Date orderDate;
    private String status;
    private List<OrderLineDTO> orderLines;


}

