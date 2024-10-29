package com.backend.OnlineStore.model.mappers;

import com.backend.OnlineStore.entity.Role;
import com.backend.OnlineStore.model.RoleDTO;
import org.mapstruct.*;

@Mapper (componentModel = "spring")
public interface RoleMapper extends AbstractMapper<RoleDTO, Role> {

}
