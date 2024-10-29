package com.backend.OnlineStore.model;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {


    private String title;
    private String description;

    private double availability;
    private double price;
    private String productType;

    private Long categoryId;  // Reference to the Category ID

    private Long authorId; // Reference to the Author ID







}
