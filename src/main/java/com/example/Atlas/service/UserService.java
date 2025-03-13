package com.example.Atlas.service;

import com.example.Atlas.model.Users;
import com.example.Atlas.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public Users createUser(Users user) {
        System.out.println("usuario recebido ao salvar" + user);
        return userRepository.save(user);
    }

    public Optional<Users> findUserById(long id) {
        return userRepository.findById(id);
    }

    public List<Users> findAllUsers() {
        return userRepository.findAll();
    }

    public Users updateUser(Long id, Users user) {
        return userRepository.save(user);
    }

    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    public void DeleteAllUsers() {
        userRepository.deleteAll();
    }
















}
