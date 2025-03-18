package com.example.Atlas.controller;

import com.example.Atlas.model.Administrador;
import com.example.Atlas.model.DTO.AuthenticationDTO;
import com.example.Atlas.model.DTO.RegisterDTO;
import com.example.Atlas.model.Professor;
import com.example.Atlas.repository.AdmRepository;
import com.example.Atlas.repository.ProfessorRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/atlas/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final ProfessorRepository professorRepository;
    private final AdmRepository admRepository;

    public AuthenticationController(AuthenticationManager authenticationManager, ProfessorRepository professorRepository,
                                    AdmRepository admRepository) {
        this.authenticationManager = authenticationManager;
        this.professorRepository = professorRepository;
        this.admRepository = admRepository;
    }

    @PostMapping("/login")
    public ResponseEntity login (@RequestBody @Valid AuthenticationDTO data) {
        try {
        var userNamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(userNamePassword);
        return ResponseEntity.ok().build();
        } catch (Exception e) {
            System.out.println("Erro ao autenticar login: " + e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/register/professor")
    public ResponseEntity registerProfessor (@RequestBody @Valid RegisterDTO data) {
        System.out.println("recebendo no controller de autenticação " + data.login());
     if(this.professorRepository.findByLogin(data.login()) != null){
         System.out.println("Erro ao registrar professor: " + data.login() + "" +  " já cadastrado!");
         return ResponseEntity.badRequest().build();
     }

     String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
     Professor newProfessor = new Professor(data.login(), encryptedPassword, data.role());

     this.professorRepository.save(newProfessor);
     return ResponseEntity.ok().build();
    }

    @PostMapping("/register/adm")
    public ResponseEntity registerAdm (@RequestBody @Valid RegisterDTO data) {
        System.out.println("recebendo no controller de autenticação " + data.login());
        if(this.admRepository.findByLogin(data.login()) != null) {
            System.out.println("Erro ao registrar adm: " + data.login() + "" +" já cadastrado!");
            return ResponseEntity.badRequest().build();
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        Administrador newAdm = new Administrador(data.login(), encryptedPassword, data.role());

        this.admRepository.save(newAdm);
        return ResponseEntity.ok().build();
    }




}
