
package com.backend.OnlineStore.controller;

import com.backend.OnlineStore.entity.Role;
import com.backend.OnlineStore.entity.User;
import com.backend.OnlineStore.exceptions.ResourceNotFoundException;
import com.backend.OnlineStore.model.UserDTO;
import com.backend.OnlineStore.repository.RoleRepository;
import com.backend.OnlineStore.repository.UserRepository;
import com.backend.OnlineStore.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.RoleNotFoundException;
import java.util.*;

@RestController
@RequestMapping("/api/user")
public class UserController {


    private final UserService userService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody User user) {
        boolean isAuthenticated = userService.authenticate(user.getEmail(), user.getPassword());

        if (isAuthenticated) {
            User authenticatedUser = userRepository.findByEmail(user.getEmail()).orElseThrow(() ->
                    new ResourceNotFoundException("User with email " + user.getEmail() + " not found"));

            Map<String, String> response = new HashMap<>();
            response.put("message", "Login successful");
            if (authenticatedUser.getRole() != null) {
                response.put("role", authenticatedUser.getRole().getRoleName());
            } else {
                response.put("error", "User role is null");
            }
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Invalid credentials"));
        }
    }




    @PostMapping("/register")
    public UserDTO registerUser(@RequestBody UserDTO userDTO) {
        return userService.registerUser(userDTO);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Optional<UserDTO>> findUserByEmail(@PathVariable String email) {
        UserDTO userDTO = userService.findUserByEmail(email);
        return ResponseEntity.ok(Optional.ofNullable(userDTO));
    }



    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable Long id) {
        return userService.findUserById(id);
    }


    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @GetMapping("/allUsers")
    public List<UserDTO> getAllUsers() {
        return userService.findAllUsers();
    }

    @PutMapping("/profile")
    public UserDTO updateUser(UserDTO userDTO) throws RoleNotFoundException {

        Optional<User> optionalUser = userRepository.findById(userDTO.getId());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setEmail(userDTO.getEmail());
            user.setCity(userDTO.getCity());
            user.setZipCode(userDTO.getZipCode());

            if (userDTO.getRoli() != null) {
                Role role = roleRepository.findById(userDTO.getRoli())
                        .orElseThrow(() -> new RoleNotFoundException("Role not found"));
                user.setRole(role);
            }

            if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
                user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            }

            userRepository.save(user);
            return userService.toDTO(user);
        }
        throw new EntityNotFoundException("User not found");
    }

}
