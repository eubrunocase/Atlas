package com.example.Atlas.repository;

import com.example.Atlas.model.Administrador;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface AdmRepository extends BaseRepository<Administrador> {
    UserDetails findByLogin(String login);
}
