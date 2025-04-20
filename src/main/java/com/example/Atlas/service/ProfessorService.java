package com.example.Atlas.service;

import com.example.Atlas.model.Professor;
import com.example.Atlas.repository.ProfessorRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ProfessorService extends BaseService<Professor> {

    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    private final ProjectService projectService;
    private final ProfessorRepository professorRepository;

    public ProfessorService(JpaRepository<Professor, Long> repository, ProjectService projectService, ProfessorRepository professorRepository) {
        super(repository);
        this.projectService = projectService;
        this.professorRepository = professorRepository;
    }

    public void deleteAll () {
        repository.deleteAll();
    }

    @Override
    public Professor save (Professor professor) {
        professor.setPassword(bCryptPasswordEncoder.encode(professor.getPassword()));
        return super.save(professor);
    }

    public Professor findyByLogin (String login) {
        Professor profile = professorRepository.findByLogin(login);
        return profile;
    }

}
