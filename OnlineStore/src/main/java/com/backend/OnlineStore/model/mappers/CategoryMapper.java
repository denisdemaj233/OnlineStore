package com.backend.OnlineStore.model.mappers;

import com.backend.OnlineStore.entity.Category;
import com.backend.OnlineStore.model.CategoryDTO;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CategoryMapper extends AbstractMapper<CategoryDTO, Category>{


}
