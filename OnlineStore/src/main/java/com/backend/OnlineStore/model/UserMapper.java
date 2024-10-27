package com.backend.OnlineStore.model;

import com.backend.OnlineStore.entity.User;
import org.mapstruct.*;
import org.mapstruct.factory.*;

@Mapper(componentModel="spring")
public interface UserMapper extends GenericMapper<UserDTO, User> {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

}
