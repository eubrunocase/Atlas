package com.example.Atlas.controller;

import com.example.Atlas.model.Users;
import com.example.Atlas.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/atlas/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Users> createUser(@RequestBody Users users) {
        System.out.println("recebido no controller" + users);
        Users savedUsers = userService.save(users);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUsers);
    }

    @GetMapping
    public List<Users> GetAllUsers() {
        return userService.findAll();
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.delete(id);
    }

    @PutMapping
    public Users update (@PathVariable Long id,@RequestBody Users users) {
        users.setId(id);
        return userService.save(users);
    }

}
