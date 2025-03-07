package com.example.Atlas.controller;

import com.example.Atlas.model.Users;
import com.example.Atlas.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/atlas/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Users> createUser(@RequestBody Users users) {
        Users savedUsers = userService.createUser(users);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUsers);
    }

    @GetMapping
    public List<Users> GetAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/{id}")
    public Optional<Users> getUserById(@PathVariable long id) {
        return userService.findUserById(id);
    }

    @DeleteMapping("/{id}")
    public void DeleteUserById(@PathVariable long id) {
        userService.deleteUser(id);
    }

    @DeleteMapping
    public void DeleteAllUsers() {
        userService.DeleteAllUsers();
    }

    @PutMapping("/{id}")
    public Users updateUserById(@PathVariable long id, @RequestBody Users users) {
        users.setId(id);
        return userService.updateUser(id, users);
    }

}
