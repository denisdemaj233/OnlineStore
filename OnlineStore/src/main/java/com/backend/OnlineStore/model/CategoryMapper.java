package com.backend.OnlineStore.model;

import com.backend.OnlineStore.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")

public interface CategoryMapper extends GenericMapper<CategoryDTO, Category> {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

}
