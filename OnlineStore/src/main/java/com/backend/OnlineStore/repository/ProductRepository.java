package com.backend.OnlineStore.repository;

import com.backend.OnlineStore.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<List<Product>> findByCategoryId(Long categoryId);

    Optional<List<Product>> findByTitleContaining(String title);


}
