package com.example.Atlas.service;

import com.example.Atlas.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService<Users> {

    public UserService(JpaRepository<Users, Long> repository) {
        super(repository);
    }
}
