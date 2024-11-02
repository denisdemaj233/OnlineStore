package com.backend.OnlineStore.model;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {


    private Long id;
    private String title;
    private String description;

    private double availability;
    private double price;
    private String productType;

    private Long categoryId;

    private Long authorId;


}
