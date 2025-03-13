package com.example.Atlas.service;

import com.example.Atlas.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ProfessorService extends BaseService<Professor> {

    public ProfessorService(JpaRepository<Professor, Long> repository) {
        super(repository);
    }
}
