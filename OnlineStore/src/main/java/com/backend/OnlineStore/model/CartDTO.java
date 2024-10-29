package com.backend.OnlineStore.model;

import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {
    private List<OrderLineDTO> orderLines;

    public double getTotalCost() {
        return orderLines.stream().mapToDouble(line -> line.getProductPrice() * line.getQuantity()).sum();
    }

}
