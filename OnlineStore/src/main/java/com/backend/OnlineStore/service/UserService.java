package com.backend.OnlineStore.service;

import com.backend.OnlineStore.entity.User;
import com.backend.OnlineStore.exceptions.ResourceNotFoundException;
import com.backend.OnlineStore.model.UserDTO;
import com.backend.OnlineStore.model.mappers.UserMapper;
import com.backend.OnlineStore.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {


    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public UserDTO registerUser(final UserDTO userDTO) {
        User user = userRepository.save(UserMapper.INSTANCE.dtoToEntity(userDTO));
        return UserMapper.INSTANCE.entityToDTO(user);
    }

    public Optional<User> findUserByEmail(String email) {
        return Optional.ofNullable(userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User with email " + email + " not found")));
    }


    public boolean userExists(String email) {
        return userRepository.existsByEmail(email);
    }


    public User updateUser(User user) {
        return userRepository.save(user);
    }


    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }


    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public boolean authenticate(String username, String password) {

        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new ResourceNotFoundException("User with email " + username + " not found"));

        if (password.matches(user.getPassword())) {
            return true;
        } else {
            return false;
        }


    }
}
