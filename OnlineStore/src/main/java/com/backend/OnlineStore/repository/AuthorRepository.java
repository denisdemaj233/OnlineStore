package com.backend.OnlineStore.repository;

import com.backend.OnlineStore.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    Optional<Author> findByFirstNameAndLastName(String firstName, String lastName);

    Optional<Author> findAuthorById(Long id);

    Author findByFirstName(String firstName);
}
