package com.backend.OnlineStore.repository;

import com.backend.OnlineStore.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {


}
