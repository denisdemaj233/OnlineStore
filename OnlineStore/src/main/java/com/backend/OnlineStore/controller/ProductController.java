package com.backend.OnlineStore.controller;

import com.backend.OnlineStore.entity.Author;
import com.backend.OnlineStore.entity.Category;
import com.backend.OnlineStore.exceptions.InvalidDataException;
import com.backend.OnlineStore.model.ProductDTO;
import com.backend.OnlineStore.repository.AuthorRepository;
import com.backend.OnlineStore.repository.CategoryRepository;
import com.backend.OnlineStore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private AuthorRepository authorRepository;


    @GetMapping("/search")
    public Optional<List<ProductDTO>> searchProducts(@RequestParam String title) {
        return productService.searchProductsByTitle(title);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        ProductDTO productDTO = productService.findProductById(id);
        return ResponseEntity.ok(productDTO);
    }


    @GetMapping("/category/{categoryId}")
    public Optional<List<ProductDTO>> getProductsByCategory(@PathVariable Long categoryId) {
        return productService.findProductsByCategory(categoryId);
    }


    @PostMapping("/admin")
    public ProductDTO createProduct(@RequestBody ProductDTO productDTO) {

        Optional<Category> category = categoryRepository.findByName(productDTO.getCategoryName());
        Author author = authorRepository.findByFirstName(productDTO.getAuthorName());


        if (category.isEmpty()) {
            throw new InvalidDataException("Category not found");
        }
        if (author == null) {
            throw new InvalidDataException("Author not found");
        }

        return productService.saveProduct(productDTO, category.orElse(null), author);
    }



    @DeleteMapping("/admin/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}
