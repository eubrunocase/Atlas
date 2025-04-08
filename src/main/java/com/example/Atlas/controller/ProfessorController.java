package com.example.Atlas.controller;

import com.example.Atlas.model.Professor;
import com.example.Atlas.service.ProfessorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("atlas/professor")
@Tag(name = "Professores", description = "Endpoints para gerenciar professores")
public class ProfessorController {

    private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @PostMapping
    @Operation(summary = "Criar professor", description = "Criar um usuário do tipo professor")
    public ResponseEntity<Professor> createProfessor(@RequestBody Professor professor) {
        System.out.println("recebido no controller" + professor);
        Professor savedProfessor = professorService.save(professor);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProfessor);
    }

    @GetMapping
    @Operation(summary = "Listar professores", description = "Retorna uma lista com todos os professores")
    public List<Professor> getAllProfessor() {
        return professorService.findAll();
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Remover professor", description = "Exclui um professor pelo id")
    public void deleteProfessor(@PathVariable Long id) {
         professorService.delete(id);
    }

    @DeleteMapping
    @Operation(summary = "Remove todos os professores", description = "Exclui todos os professores cadastrados")
    public void deleteAll () {
        professorService.deleteAll();
    }

    @PutMapping("{id}")
    @Operation(summary = "Atualiza um professor", description = "Atualiza as informações de um professor pelo id")
    public Professor updateProfessor(@PathVariable Long id, @RequestBody Professor professor) {
        professor.setId(id);
        return professorService.save(professor);
    }


}
