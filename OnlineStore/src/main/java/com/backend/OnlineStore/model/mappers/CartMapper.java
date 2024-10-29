package com.backend.OnlineStore.model.mappers;

import com.backend.OnlineStore.entity.Cart;
import com.backend.OnlineStore.model.CartDTO;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CartMapper extends AbstractMapper<CartDTO, Cart>{


}
