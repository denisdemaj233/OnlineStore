package com.backend.OnlineStore.model.mappers;

import com.backend.OnlineStore.entity.OrderLine;
import com.backend.OnlineStore.model.OrderLineDTO;
import org.mapstruct.*;

@Mapper (componentModel = "spring")
public abstract class OrderLineMapper implements AbstractMapper<OrderLineDTO, OrderLine> {

}