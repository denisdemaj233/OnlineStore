package com.backend.OnlineStore.model;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface GenericMapper<D, E> {

    GenericMapper INSTANCE = Mappers.getMapper(GenericMapper.class);
    D entityToDTO(E entity);
    E dtoToEntity(D dto);
}