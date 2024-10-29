package com.backend.OnlineStore.service;

import com.backend.OnlineStore.entity.Category;
import com.backend.OnlineStore.exceptions.InvalidDataException;
import com.backend.OnlineStore.exceptions.ResourceNotFoundException;
import com.backend.OnlineStore.model.CategoryDTO;
import com.backend.OnlineStore.model.mappers.CategoryMapper;
import com.backend.OnlineStore.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }


    public CategoryDTO saveCategory(final CategoryDTO categoryDTO) {

        Category entity = categoryMapper.toEntity(categoryDTO);
        Category save = categoryRepository.save(entity);
        return categoryMapper.toDTO(save);
    }

    public List<CategoryDTO> findAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(categoryMapper::toDTO)
                .toList();
    }


    public CategoryDTO findCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category with id " + id + " not found"));
        return categoryMapper.toDTO(category);
    }


    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
