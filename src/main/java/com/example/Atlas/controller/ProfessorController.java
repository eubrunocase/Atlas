package com.example.Atlas.controller;

import com.example.Atlas.model.Professor;
import com.example.Atlas.service.ProfessorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("atlas/professor")
public class ProfessorController {

    private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @PostMapping
    public ResponseEntity<Professor> createProfessor(@RequestBody Professor professor) {
        System.out.println("recebido no controller" + professor);
        Professor savedProfessor = professorService.save(professor);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProfessor);
    }

    @GetMapping
    public List<Professor> getAllProfessor() {
        return professorService.findAll();
    }

    @DeleteMapping("{id}")
    public void deleteProfessor(@PathVariable Long id) {
        professorService.delete(id);
    }

    @PutMapping("{id}")
    public Professor updateProfessor(@PathVariable Long id, @RequestBody Professor professor) {
        professor.setId(id);
        return professorService.save(professor);
    }
}
