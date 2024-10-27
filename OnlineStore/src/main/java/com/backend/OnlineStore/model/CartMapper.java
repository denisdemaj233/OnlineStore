package com.backend.OnlineStore.model;

import com.backend.OnlineStore.entity.Cart;
import org.mapstruct.factory.Mappers;

public interface CartMapper extends GenericMapper<CartDTO, Cart>{

    CartMapper INSTANCE = Mappers.getMapper(CartMapper.class);

}
