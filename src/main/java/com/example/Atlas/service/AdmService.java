package com.example.Atlas.service;

import com.example.Atlas.model.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class AdmService extends BaseService<Administrador> {

    public AdmService(JpaRepository<Administrador, Long> repository) {
        super(repository);
    }
}
