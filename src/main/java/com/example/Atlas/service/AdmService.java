package com.example.Atlas.service;

import com.example.Atlas.model.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdmService extends BaseService<Administrador> {

    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public AdmService(JpaRepository<Administrador, Long> repository, CryptPasswordService cryptPasswordService) {
        super(repository);
    }

    @Override
    public Administrador save(Administrador administrador) {
        administrador.setPassword(bCryptPasswordEncoder.encode(administrador.getPassword()));
        return super.save(administrador);
    }



}
