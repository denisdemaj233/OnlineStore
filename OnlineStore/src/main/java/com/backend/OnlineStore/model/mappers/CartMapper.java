package com.backend.OnlineStore.model.mappers;

import com.backend.OnlineStore.entity.Cart;
import com.backend.OnlineStore.model.CartDTO;
import org.mapstruct.factory.Mappers;

public interface CartMapper extends GenericMapper<CartDTO, Cart>{

    CartMapper INSTANCE = Mappers.getMapper(CartMapper.class);

}
