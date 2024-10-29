package com.backend.OnlineStore.controller;

import com.backend.OnlineStore.entity.User;
import com.backend.OnlineStore.model.UserDTO;
import com.backend.OnlineStore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {


    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {

        boolean isAuthenticated = userService.authenticate(user.getEmail(), user.getPassword());

        if (isAuthenticated) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
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


    @PutMapping("/profile")
    public UserDTO updateUser(@RequestBody UserDTO userDTO) {
        return userService.updateUser(userDTO);
    }


    @GetMapping("/{id}")
    public UserDTO getUserById(@PathVariable Long id) {
        return userService.findUserById(id);
    }


    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
