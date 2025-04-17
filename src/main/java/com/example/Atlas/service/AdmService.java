package com.example.Atlas.service;

import com.example.Atlas.model.Administrador;
import com.example.Atlas.repository.AdmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdmService extends BaseService<Administrador> {

    @Autowired
    private AdmRepository admRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public AdmService(JpaRepository<Administrador, Long> repository, CryptPasswordService cryptPasswordService) {
        super(repository);
    }

    @Override
    public Administrador save(Administrador administrador) {
        administrador.setPassword(bCryptPasswordEncoder.encode(administrador.getPassword()));
        return super.save(administrador);
    }

    public void deleteAll () {
        repository.deleteAll();
    }

    public Administrador findByLogin(String login) {
        Administrador profile =  admRepository.findByLogin(login);
        return profile;
    }

}
