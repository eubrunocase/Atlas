package com.example.Atlas.repository;

import com.example.Atlas.model.Professor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends BaseRepository<Professor> {
    Professor findByLogin(String login);
}
