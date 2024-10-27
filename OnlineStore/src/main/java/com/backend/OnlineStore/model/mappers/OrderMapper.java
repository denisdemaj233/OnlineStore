package com.backend.OnlineStore.model.mappers;

import com.backend.OnlineStore.entity.Order;
import com.backend.OnlineStore.model.OrderDTO;
import org.mapstruct.*;
import org.mapstruct.factory.*;

@Mapper(componentModel = "spring")
public interface OrderMapper{

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);
    OrderDTO orderToOrderDTO(Order order);
    Order orderDTOToOrder(OrderDTO orderDTO);

}
