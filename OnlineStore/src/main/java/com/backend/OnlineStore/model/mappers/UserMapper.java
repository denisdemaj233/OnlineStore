package com.backend.OnlineStore.model.mappers;

import com.backend.OnlineStore.entity.User;
import com.backend.OnlineStore.model.UserDTO;
import org.mapstruct.*;
import org.mapstruct.factory.*;

@Mapper(componentModel="spring")
public interface UserMapper extends GenericMapper<UserDTO, User> {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

}
