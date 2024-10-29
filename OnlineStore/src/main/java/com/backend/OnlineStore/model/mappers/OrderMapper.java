package com.backend.OnlineStore.model.mappers;

import com.backend.OnlineStore.entity.Order;
import com.backend.OnlineStore.model.OrderDTO;
import org.mapstruct.*;

@Mapper (componentModel = "spring")
public interface OrderMapper extends AbstractMapper<OrderDTO, Order> {

}