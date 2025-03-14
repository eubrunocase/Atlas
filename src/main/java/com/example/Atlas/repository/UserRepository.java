package com.example.Atlas.repository;

import com.example.Atlas.model.Users;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<Users> {
    UserDetails findByLogin(String login);
}
