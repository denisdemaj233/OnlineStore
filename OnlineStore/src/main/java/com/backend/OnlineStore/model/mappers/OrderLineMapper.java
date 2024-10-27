package com.backend.OnlineStore.model.mappers;

import com.backend.OnlineStore.entity.OrderLine;
import com.backend.OnlineStore.model.OrderLineDTO;

import org.mapstruct.*;
import org.mapstruct.factory.*;

@Mapper(componentModel = "spring")
public interface OrderLineMapper{
    OrderLineMapper INSTANCE = Mappers.getMapper(OrderLineMapper.class);

     OrderLineDTO orderLineToOrderLineDTO(OrderLine orderLine);

     OrderLine orderLineDTOToOrderLine(OrderLineDTO orderLineDTO);
}
