package com.backend.OnlineStore.model.mappers;

import com.backend.OnlineStore.entity.Order;
import com.backend.OnlineStore.model.OrderDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")

public interface OrderMapper extends GenericMapper<OrderDTO, Order>{

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

}
