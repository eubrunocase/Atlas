package com.example.Atlas.controller;

import com.example.Atlas.model.Users;
import com.example.Atlas.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/atlas/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<Users> createUser(@RequestBody Users users) {
        Users savedUsers = userRepository.save(users);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUsers);
    }

    @GetMapping
    public List<Users> GetAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Users> getUsersById(@PathVariable Long id) {
        return userRepository.findById(id);
    }

    @DeleteMapping("/{id}")
    public void DeleteUserById(@PathVariable Long id) {
        userRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public Users updateUserById(@PathVariable Long id, Users Users) {
        return userRepository.save(Users);
    }

    @DeleteMapping
    public void deleteAllUsers() {
        userRepository.deleteAll();
    }


}
