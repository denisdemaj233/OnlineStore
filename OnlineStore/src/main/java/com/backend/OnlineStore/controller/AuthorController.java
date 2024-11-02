package com.backend.OnlineStore.controller;

import com.backend.OnlineStore.model.AuthorDTO;
import com.backend.OnlineStore.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin/authors")
public class AuthorController {


    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public ResponseEntity<List<AuthorDTO>> getAllAuthors() {
        List<AuthorDTO> authors = authorService.getAllAuthors();
        return ResponseEntity.ok(authors);
    }

    @PostMapping
    public AuthorDTO saveAuthor(@RequestBody AuthorDTO authorDTO) {
        return authorService.saveAuthor(authorDTO);
    }


    @GetMapping("/{id}")
    public Optional<AuthorDTO> getAuthorById(@PathVariable Long id) {
        return authorService.findAuthorById(id);
    }


    @GetMapping("/search")
    public Optional<AuthorDTO> getAuthorByName(@RequestParam String firstName, @RequestParam String lastName) {
        return authorService.findAuthorByName(firstName, lastName);
    }

    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
    }
}
