package com.example.Atlas.service;

import com.example.Atlas.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService<Users> {

    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public UserService(JpaRepository<Users, Long> repository) {
        super(repository);

    }
    @Override
    public Users save (Users user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
         return super.save(user);
    }
}
