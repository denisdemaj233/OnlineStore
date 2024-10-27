package com.backend.OnlineStore.model.mappers;

import com.backend.OnlineStore.entity.Cart;
import com.backend.OnlineStore.model.CartDTO;
import org.mapstruct.*;
import org.mapstruct.factory.*;

@Mapper(componentModel = "spring")
public interface CartMapper{

    CartMapper INSTANCE = Mappers.getMapper(CartMapper.class);
    CartDTO cartToCartDTO(Cart cart);
    Cart cartDTOToCart(CartDTO cartDTO);
}
