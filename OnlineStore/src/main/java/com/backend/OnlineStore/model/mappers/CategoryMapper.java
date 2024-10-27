package com.backend.OnlineStore.model.mappers;

import com.backend.OnlineStore.entity.Category;
import com.backend.OnlineStore.model.CategoryDTO;
import org.mapstruct.*;
import org.mapstruct.factory.*;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryDTO categoryToCategoryDTO(Category category);

    Category categoryDTOToCategory(CategoryDTO categoryDTO);

}
