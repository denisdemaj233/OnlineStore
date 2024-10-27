package com.backend.OnlineStore.model.mappers;

import com.backend.OnlineStore.entity.OrderLine;
import com.backend.OnlineStore.model.OrderLineDTO;
import org.mapstruct.factory.Mappers;

public interface OrderLineMapper extends GenericMapper<OrderLineDTO, OrderLine>{
    OrderLineMapper INSTANCE= Mappers.getMapper(OrderLineMapper.class);
}
