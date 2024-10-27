package com.backend.OnlineStore.model.mappers;

import com.backend.OnlineStore.entity.Product;
import com.backend.OnlineStore.model.ProductDTO;
import org.mapstruct.*;
import org.mapstruct.factory.*;

@Mapper(componentModel="spring")
public interface ProductMapper{

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDTO productToProductDTO(Product product);

    Product productDTOToProduct(ProductDTO productDTO);
}
