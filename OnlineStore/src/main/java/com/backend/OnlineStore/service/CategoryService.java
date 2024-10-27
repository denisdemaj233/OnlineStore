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

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    public CategoryDTO saveCategory(CategoryDTO categoryDTO) {
        if (categoryDTO.getName() == null || categoryDTO.getName().isEmpty()) {
            throw new InvalidDataException("Category name is required");
        }


        Category category = CategoryMapper.INSTANCE.categoryDTOToCategory(categoryDTO);
        Category savedCategory = categoryRepository.save(category);
        return CategoryMapper.INSTANCE.categoryToCategoryDTO(savedCategory);
    }

    public List<CategoryDTO> findAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(CategoryMapper.INSTANCE::categoryToCategoryDTO)
                .toList();
    }


    public CategoryDTO findCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category with id " + id + " not found"));
        return CategoryMapper.INSTANCE.categoryToCategoryDTO(category);
    }


    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
