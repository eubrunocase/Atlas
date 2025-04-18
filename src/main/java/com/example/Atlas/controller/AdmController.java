package com.example.Atlas.controller;

import com.example.Atlas.infra.TokenService;
import com.example.Atlas.model.Administrador;
import com.example.Atlas.service.AdmService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/atlas/adm")
@Tag(name = "Administradores", description = "Endpoints para gerenciar administradores")
public class AdmController {

    private final AdmService admService;
    private final TokenService tokenService;

    public AdmController(AdmService admService, TokenService tokenService) {
        this.admService = admService;
        this.tokenService = tokenService;
    }

    @PostMapping
    @Operation(summary = "Cria novo administrador", description = "Metodo para criar usuário de administrador")
    public ResponseEntity<Administrador> createAdm (@RequestBody Administrador administrador) {
        System.out.println("recebendo ADM no controller " + administrador);
        Administrador savedADM = admService.save(administrador);
        return ResponseEntity.status((HttpStatus.CREATED)).body(savedADM);
    }

    @GetMapping
    @Operation(summary = "Listar administradores", description = "Retorna uma lista com todos os administradores")
    public List<Administrador> GetAllAdm () {
        return admService.findAll();
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Deletar administrador", description = "Remove um administrador passando o seu id")
    public void deleteAdmById (@PathVariable Long id) {
        admService.delete(id);
    }

    @DeleteMapping
    @Operation(summary = "Deletar todos administradores", description = "Exclui todos os administradores cadastrados")
    public void deleteAllAdm () {
         admService.deleteAll();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar administrador", description = "Atualiza as informações de um administrador pelo id")
    public Administrador updateAdmById (@PathVariable Long id,@RequestBody Administrador administrador) {
        administrador.setId(id);
        return admService.save(administrador);
    }

    @GetMapping("/profile")
    @Operation(summary = "Recuperar informações", description = "Recupera as informações do usuário")
    public ResponseEntity<Administrador> findByToken (@RequestHeader("Authorization") String auth) {
          String token = auth.replace("Bearer ", "").trim();

          String login = tokenService.validadeToken(token);

          Administrador profile = admService.findByLogin(auth);

          return ResponseEntity.ok(profile);

    }

}
