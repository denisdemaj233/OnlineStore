package com.backend.OnlineStore.model.mappers;

import com.backend.OnlineStore.entity.User;
import com.backend.OnlineStore.model.UserDTO;
import org.mapstruct.*;
import org.mapstruct.factory.*;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO userToUserDTO(User user);

    User userDTOToUser(UserDTO userDTO);

}
