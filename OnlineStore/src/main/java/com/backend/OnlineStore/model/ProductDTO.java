package com.backend.OnlineStore.model;

import lombok.Data;

@Data
public class ProductDTO {

    private String title;
    private String description;
    private String thumbnailUrl;
    private double price;
    private String productType;

    private String categoryName;

    private String authorName;


}
