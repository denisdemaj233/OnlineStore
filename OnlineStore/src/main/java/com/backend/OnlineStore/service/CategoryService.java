package com.backend.OnlineStore.service;

import com.backend.OnlineStore.entity.Category;
import com.backend.OnlineStore.exceptions.InvalidDataException;
import com.backend.OnlineStore.exceptions.ResourceNotFoundException;
import com.backend.OnlineStore.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;


    public Category saveCategory(Category category) {
        if (category.getName() == null || category.getName().isEmpty()) {
            throw new InvalidDataException("Category name is required");
        }
        return categoryRepository.save(category);
    }


    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }




    public Category findCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category with id " + id + " not found"));
    }



    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
