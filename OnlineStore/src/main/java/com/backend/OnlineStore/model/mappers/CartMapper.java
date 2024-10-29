package com.backend.OnlineStore.model.mappers;

import com.backend.OnlineStore.entity.Cart;
import com.backend.OnlineStore.model.CartDTO;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public abstract class CartMapper implements AbstractMapper<CartDTO, Cart>{


}
