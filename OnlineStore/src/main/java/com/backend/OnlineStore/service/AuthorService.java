package com.backend.OnlineStore.service;

import com.backend.OnlineStore.entity.Author;
import com.backend.OnlineStore.exceptions.ResourceNotFoundException;
import com.backend.OnlineStore.model.AuthorDTO;
import com.backend.OnlineStore.model.mappers.AuthorMapper;
import com.backend.OnlineStore.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.Optional;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    @Autowired
    public AuthorService(AuthorRepository authorRepository, AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }

    public AuthorDTO saveAuthor(AuthorDTO authorDTO) {

        Author author = authorMapper.toEntity(authorDTO);
        Author savedAuthor = authorRepository.save(author);
        return authorMapper.toDTO(savedAuthor);
    }


    public Optional<AuthorDTO> findAuthorById(Long id) {
        return Optional.ofNullable(authorRepository.findById(id)
                .map(authorMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found")));
    }


    public Optional<AuthorDTO> findAuthorByName(String firstName, String lastName) {
        return Optional.ofNullable(authorRepository.findByFirstNameAndLastName(firstName, lastName)
                .map(authorMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found")));
    }

    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }
}
