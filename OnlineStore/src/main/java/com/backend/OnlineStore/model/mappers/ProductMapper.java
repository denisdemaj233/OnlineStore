package com.backend.OnlineStore.model.mappers;

import com.backend.OnlineStore.entity.Product;
import com.backend.OnlineStore.model.ProductDTO;
import org.mapstruct.*;

@Mapper (componentModel = "spring")
public abstract class ProductMapper implements AbstractMapper<ProductDTO, Product> {

}