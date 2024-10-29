package com.backend.OnlineStore.service;

import com.backend.OnlineStore.entity.User;
import com.backend.OnlineStore.exceptions.ResourceNotFoundException;
import com.backend.OnlineStore.model.UserDTO;
import com.backend.OnlineStore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO toDTO(User user) {
        if (user == null) {
            return null;
        }
        return new UserDTO(user.getEmail(), user.getCity(), user.getZipCode());
    }


    public User toEntity(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setCity(userDTO.getCity());
        user.setZipCode(userDTO.getZipCode());
        return user;
    }



    public UserDTO registerUser(final UserDTO userDTO) {

        return toDTO(userRepository.save(toEntity(userDTO)));
    }

    public UserDTO findUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User with email " + email + " not found"));

        return toDTO(user);
    }


    public boolean userExists(String email) {
        return userRepository.existsByEmail(email);
    }


    public UserDTO updateUser(UserDTO userDTO) {
        User user = userRepository.save(toEntity(userDTO));
        return toDTO(user);
    }


    public UserDTO findUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with ID " + id + " not found"));

        return toDTO(user);
    }


    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }


    public boolean authenticate(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User with email " + email + " not found"));

        return password.matches(user.getPassword());
    }
}
