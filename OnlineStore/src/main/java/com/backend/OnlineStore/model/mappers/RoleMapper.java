package com.backend.OnlineStore.model.mappers;

import com.backend.OnlineStore.entity.Role;
import com.backend.OnlineStore.model.RoleDTO;
import org.mapstruct.*;
import org.mapstruct.factory.*;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    RoleDTO roleToRoleDTO(Role role);
    Role roleDTOToRole(RoleDTO roleDTO);

}
