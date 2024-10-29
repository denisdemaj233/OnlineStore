package com.backend.OnlineStore.service;

import com.backend.OnlineStore.entity.Category;
import com.backend.OnlineStore.exceptions.ResourceNotFoundException;
import com.backend.OnlineStore.model.CategoryDTO;
import com.backend.OnlineStore.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;

    }


    public CategoryDTO toDTO(Category category) {
        if (category == null) {
            return null;
        }
        return new CategoryDTO(category.getName());
    }


    public Category toEntity(CategoryDTO categoryDTO) {
        if (categoryDTO == null) {
            return null;
        }
        Category category = new Category();
        category.setName(categoryDTO.getName());
        return category;
    }

    public CategoryDTO saveCategory(final CategoryDTO categoryDTO) {

        Category entity = toEntity(categoryDTO);
        Category save = categoryRepository.save(entity);
        return toDTO(save);
    }

    public List<CategoryDTO> findAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(this::toDTO)
                .toList();
    }


    public CategoryDTO findCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category with id " + id + " not found"));
        return toDTO(category);
    }


    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
