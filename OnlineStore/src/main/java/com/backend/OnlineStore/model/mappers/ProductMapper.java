package com.backend.OnlineStore.model.mappers;

import com.backend.OnlineStore.entity.Product;
import com.backend.OnlineStore.model.ProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel="spring")

public interface ProductMapper extends GenericMapper<ProductDTO, Product>{

    ProductMapper INSTANCE= Mappers.getMapper(ProductMapper.class);
}
