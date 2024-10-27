package com.backend.OnlineStore.model.mappers;

import com.backend.OnlineStore.entity.Category;
import com.backend.OnlineStore.model.CategoryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")

public interface CategoryMapper extends GenericMapper<CategoryDTO, Category> {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

}
