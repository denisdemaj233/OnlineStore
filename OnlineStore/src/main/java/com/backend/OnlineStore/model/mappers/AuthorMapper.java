package com.backend.OnlineStore.model.mappers;

import com.backend.OnlineStore.entity.Author;
import com.backend.OnlineStore.model.AuthorDTO;
import org.mapstruct.*;
import org.mapstruct.factory.*;

@Mapper(componentModel = "spring")
public interface AuthorMapper{
    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);

    AuthorDTO authorToAuthorDTO(Author author);

    Author authorDTOToAuthor(AuthorDTO authorDTO);

}
