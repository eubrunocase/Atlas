package com.example.Atlas.service;

import com.example.Atlas.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ProfessorService extends BaseService<Professor> {

    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public ProfessorService(JpaRepository<Professor, Long> repository) {
        super(repository);
    }

    public void deleteAll () {
        repository.deleteAll();
    }

    @Override
    public Professor save (Professor professor) {
        professor.setPassword(bCryptPasswordEncoder.encode(professor.getPassword()));
        return super.save(professor);
    }
}
