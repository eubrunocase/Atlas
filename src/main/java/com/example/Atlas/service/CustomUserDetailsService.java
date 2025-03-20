package com.example.Atlas.service;

import com.example.Atlas.repository.AdmRepository;
import com.example.Atlas.repository.ProfessorRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Primary
public class CustomUserDetailsService implements UserDetailsService {

    private final AdmRepository admRepository;
    private final ProfessorRepository professorRepository;

    public CustomUserDetailsService(AdmRepository admRepository, ProfessorRepository professorRepository) {
        this.admRepository = admRepository;
        this.professorRepository = professorRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userAdm = admRepository.findByLogin(username);
        if (userAdm != null) {
            return userAdm;
        }

        UserDetails userProfessor = professorRepository.findByLogin(username);
        if (userProfessor != null) {
            return userProfessor;
        }

        throw new UsernameNotFoundException("Usuário não encontrado: " + username);
    }
}
