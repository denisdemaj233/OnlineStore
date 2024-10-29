package com.backend.OnlineStore.service;

import com.backend.OnlineStore.entity.Author;
import com.backend.OnlineStore.exceptions.ResourceNotFoundException;
import com.backend.OnlineStore.model.AuthorDTO;
import com.backend.OnlineStore.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;


    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;

    }

    public Author toEntity(AuthorDTO dto) {
        if (dto == null) {
            return null;
        }

        Author author = new Author();
        author.setFirstName(dto.getFirstName());
        author.setLastName(dto.getLastName());
        return author;
    }


    public AuthorDTO toDTO(Author entity) {
        if (entity == null) {
            return null;
        }

        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setFirstName(entity.getFirstName());
        authorDTO.setLastName(entity.getLastName());
        return authorDTO;
    }

    public AuthorDTO saveAuthor(AuthorDTO authorDTO) {

        Author author = toEntity(authorDTO);
        Author savedAuthor = authorRepository.save(author);
        return toDTO(savedAuthor);
    }


    public Optional<AuthorDTO> findAuthorById(Long id) {
        return Optional.ofNullable(authorRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found")));
    }


    public Optional<AuthorDTO> findAuthorByName(String firstName, String lastName) {
        return Optional.ofNullable(authorRepository.findByFirstNameAndLastName(firstName, lastName)
                .map(this::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found")));
    }

    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }
}
