package com.backend.OnlineStore.model;

import com.backend.OnlineStore.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel="spring")

public interface ProductMapper extends GenericMapper<ProductDTO, Product>{

    ProductMapper INSTANCE= Mappers.getMapper(ProductMapper.class);
}
