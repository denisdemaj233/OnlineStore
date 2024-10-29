package com.backend.OnlineStore.model.mappers;

import com.backend.OnlineStore.entity.User;
import com.backend.OnlineStore.model.UserDTO;
import org.mapstruct.*;

@Mapper (componentModel = "spring")
public abstract class UserMapper implements AbstractMapper<UserDTO, User> {

}



