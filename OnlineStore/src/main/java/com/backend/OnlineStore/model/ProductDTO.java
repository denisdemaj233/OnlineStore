package com.backend.OnlineStore.model;

import lombok.Data;

@Data
public class ProductDTO {
    private Long id;
    private String title;
    private String description;
    private String thumbnailUrl;
    private double price;
    private String productType;
    private Long categoryId;
    private String categoryName;
    private Long authorId;
    private String authorName;


}
