package com.backend.OnlineStore.controller;

import com.backend.OnlineStore.entity.Author;
import com.backend.OnlineStore.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/admin/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;


    @PostMapping
    public Author saveAuthor(@RequestBody Author author) {
        return authorService.saveAuthor(author);
    }


    @GetMapping("/{id}")
    public Optional<Author> getAuthorById(@PathVariable Long id) {
        return authorService.findAuthorById(id);
    }


    @GetMapping("/search")
    public Author getAuthorByName(@RequestParam String firstName, @RequestParam String lastName) {
        return authorService.findAuthorByName(firstName, lastName);
    }

    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
    }
}
