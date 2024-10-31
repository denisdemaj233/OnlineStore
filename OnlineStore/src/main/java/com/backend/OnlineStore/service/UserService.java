package com.backend.OnlineStore.service;

import com.backend.OnlineStore.entity.User;
import com.backend.OnlineStore.exceptions.ResourceNotFoundException;
import com.backend.OnlineStore.model.UserDTO;
import com.backend.OnlineStore.repository.RoleRepository;
import com.backend.OnlineStore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    public UserDTO toDTO(User user) {
        if (user == null) {
            return null;
        }

        Long roleId=user.getRole()!=null?user.getRole().getId():null;


        return new UserDTO(
                user.getId(),
                user.getEmail(),
                user.getCity(),
                user.getZipCode(),
                roleId,
                user.getPassword()
        );
    }


    public User toEntity(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setCity(userDTO.getCity());
        user.setZipCode(userDTO.getZipCode());
        user.setRole(roleRepository.findRoleById(userDTO.getRoli()));
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
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


//    public boolean authenticate(String email, String password) {
//        User user = userRepository.findByEmail(email)
//                .orElseThrow(() -> new ResourceNotFoundException("User with email " + email + " not found"));
//
//        return passwordEncoder.matches(password, user.getPassword());
//    }

    public boolean authenticate(String email, String password) {
        System.out.println("Authenticating user with email: " + email);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User with email " + email + " not found"));

        boolean isPasswordMatch = passwordEncoder.matches(password, user.getPassword());
        System.out.println("Password match: " + isPasswordMatch);
        return isPasswordMatch;
    }


    public Optional<User> findById(Long userId) {
        return userRepository.findById(userId);
    }

    public List<UserDTO> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(this::toDTO).toList();
    }
}
