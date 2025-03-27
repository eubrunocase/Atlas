package com.example.Atlas.controller;

import com.example.Atlas.infra.TokenService;
import com.example.Atlas.model.Administrador;
import com.example.Atlas.model.DTO.AuthenticationDTO;
import com.example.Atlas.model.DTO.LoginResponseDTO;
import com.example.Atlas.model.DTO.RegisterDTO;
import com.example.Atlas.model.Professor;
import com.example.Atlas.model.Users;
import com.example.Atlas.repository.AdmRepository;
import com.example.Atlas.repository.ProfessorRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/atlas/auth")
@Tag(name = "Autenticação", description = "Gerencia a autenticação de usuários")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final ProfessorRepository professorRepository;
    private final AdmRepository admRepository;
    private final TokenService tokenService;

    public AuthenticationController(AuthenticationManager authenticationManager, ProfessorRepository professorRepository,
                                    AdmRepository admRepository, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.professorRepository = professorRepository;
        this.admRepository = admRepository;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    @Operation(summary = "Login", description = "Realiza o login de usuário")
    public ResponseEntity<LoginResponseDTO> login (@RequestBody AuthenticationDTO data) {
        try {
            System.out.println("recebendo tentativa de login para  " + data.login());
        Authentication authenticationRequest = UsernamePasswordAuthenticationToken.unauthenticated(data.login(), data.password());
        Authentication authenticationResponse = this.authenticationManager.authenticate(authenticationRequest);
        var token = tokenService.generateToken((Users) authenticationResponse.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
        } catch (Exception e) {
            System.err.println("Erro ao tentar login para  " + data.login());
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/register/professor")
    @Operation(summary = "Registrar novo professor", description = "Registra um novo professor")
    public ResponseEntity<Professor> registerProfessor (@RequestBody @Valid RegisterDTO data) {
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
    @Operation(summary = "Registrar novo adm", description = "Registra um novo adm")
    public ResponseEntity<Administrador> registerAdm (@RequestBody @Valid RegisterDTO data) {
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
