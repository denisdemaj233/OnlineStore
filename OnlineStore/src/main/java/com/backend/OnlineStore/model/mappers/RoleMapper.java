package com.backend.OnlineStore.model.mappers;

import com.backend.OnlineStore.entity.Role;
import com.backend.OnlineStore.model.RoleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RoleMapper extends GenericMapper<RoleDTO, Role>{

    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

}
