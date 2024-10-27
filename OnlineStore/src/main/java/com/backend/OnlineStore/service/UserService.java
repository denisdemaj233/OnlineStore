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

        User user = UserMapper.INSTANCE.userDTOToUser(userDTO);
        User savedUser = userRepository.save(user);
        return UserMapper.INSTANCE.userToUserDTO(savedUser);
    }

    public UserDTO findUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User with email " + email + " not found"));

        return UserMapper.INSTANCE.userToUserDTO(user);
    }


    public boolean userExists(String email) {
        return userRepository.existsByEmail(email);
    }


    public UserDTO updateUser(UserDTO userDTO) {
        User user = userRepository.save(UserMapper.INSTANCE.userDTOToUser(userDTO));
        return UserMapper.INSTANCE.userToUserDTO(user);
    }


    public UserDTO findUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with ID " + id + " not found"));

        return UserMapper.INSTANCE.userToUserDTO(user);
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
