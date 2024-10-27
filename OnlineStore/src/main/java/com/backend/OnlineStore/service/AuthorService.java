package com.backend.OnlineStore.service;

import com.backend.OnlineStore.entity.Author;
import com.backend.OnlineStore.model.AuthorDTO;
import com.backend.OnlineStore.model.mappers.AuthorMapper;
import com.backend.OnlineStore.repository.AuthorRepository;
import org.springframework.stereotype.Service;



import java.util.Optional;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public AuthorDTO saveAuthor(AuthorDTO authorDTO) {

        Author author = AuthorMapper.INSTANCE.authorDTOToAuthor(authorDTO);
        Author savedAuthor = authorRepository.save(author);
        return AuthorMapper.INSTANCE.authorToAuthorDTO(savedAuthor);
    }


    public Optional<AuthorDTO> findAuthorById(Long id) {
        return authorRepository.findById(id)
                .map(AuthorMapper.INSTANCE::authorToAuthorDTO);
    }


    public Optional<AuthorDTO> findAuthorByName(String firstName, String lastName) {
        return authorRepository.findByFirstNameAndLastName(firstName, lastName)
                .map(AuthorMapper.INSTANCE::authorToAuthorDTO);
    }

    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }
}
