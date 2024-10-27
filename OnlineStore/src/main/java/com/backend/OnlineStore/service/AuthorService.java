package com.backend.OnlineStore.service;

import com.backend.OnlineStore.entity.Author;
import com.backend.OnlineStore.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;


    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }


    public Optional<Author> findAuthorById(Long id) {
        return authorRepository.findById(id);
    }


    public Optional<Author> findAuthorByName(String firstName, String lastName) {
        return authorRepository.findByFirstNameAndLastName(firstName, lastName);
    }


    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }
}
