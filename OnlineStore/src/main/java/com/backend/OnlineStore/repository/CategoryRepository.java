package com.backend.OnlineStore.repository;

import com.backend.OnlineStore.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findById(Long id);

    Optional<Category> findByName(String categoryName);
}
