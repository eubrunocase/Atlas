package com.example.Atlas.service;

import com.example.Atlas.repository.AdmRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationServiceAdm implements UserDetailsService {

    private final AdmRepository admRepository;

    public AuthorizationServiceAdm(AdmRepository admRepository) {
        this.admRepository = admRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return admRepository.findByLogin(username);
    }
}
