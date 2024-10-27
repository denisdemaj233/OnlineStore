package com.backend.OnlineStore.model;

import com.backend.OnlineStore.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RoleMapper extends GenericMapper<RoleDTO, Role>{

    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

}
