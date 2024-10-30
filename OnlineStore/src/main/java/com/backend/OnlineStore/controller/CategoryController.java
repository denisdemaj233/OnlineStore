package com.backend.OnlineStore.controller;

import com.backend.OnlineStore.model.CategoryDTO;
import com.backend.OnlineStore.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/categories")
public class CategoryController {


    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping
    public List<CategoryDTO> getAllCategories() {
        return categoryService.findAllCategories();
    }

    @PostMapping("/register")
    public CategoryDTO createCategory(@RequestBody CategoryDTO categoryDTO){
        return categoryService.saveCategory(categoryDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long id) {
        CategoryDTO categoryDTO = categoryService.findCategoryById(id);
        return ResponseEntity.ok(categoryDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }
}
