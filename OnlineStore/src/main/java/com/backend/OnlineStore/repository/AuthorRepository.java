package com.backend.OnlineStore.repository;

import com.backend.OnlineStore.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    Author findByFirstNameAndLastName(String firstName, String lastName);
}
