package com.backend.OnlineStore.model;

import com.backend.OnlineStore.entity.OrderLine;
import org.mapstruct.factory.Mappers;

public interface OrderLineMapper extends GenericMapper<OrderLineDTO, OrderLine>{
    OrderLineMapper INSTANCE= Mappers.getMapper(OrderLineMapper.class);
}
