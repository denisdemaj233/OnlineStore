package com.backend.OnlineStore.model;

import com.backend.OnlineStore.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")

public interface OrderMapper extends GenericMapper<OrderDTO, Order>{

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

}
