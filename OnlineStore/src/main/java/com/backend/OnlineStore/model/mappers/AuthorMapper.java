package com.backend.OnlineStore.model.mappers;

import com.backend.OnlineStore.entity.Author;
import com.backend.OnlineStore.model.AuthorDTO;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public abstract class AuthorMapper implements AbstractMapper<AuthorDTO, Author>{

}

