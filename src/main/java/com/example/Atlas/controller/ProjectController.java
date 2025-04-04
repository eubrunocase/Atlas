package com.example.Atlas.controller;

import com.example.Atlas.model.DTO.UpdateStatusProjectDTO;
import com.example.Atlas.model.Project;
import com.example.Atlas.model.enums.ProjectStatus;
import com.example.Atlas.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/atlas/project")
@Tag(name = "Projetos", description = "Endpoints para gerenciar projetos")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping
    @Operation(summary = "Criar um novo projeto", description = "Cria um novo projeto")
    public ResponseEntity<Project> createProject(@RequestBody Project project) {
        System.out.println("recebendo projeto no controller" + project);
        Project savedProject = projectService.createProject(project);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProject);
    }

    @GetMapping
    @Operation(summary = "Listar projetos", description = "Retorna uma lista com todos os projetos")
    public List<Project> getAll () {
        return projectService.findAll();
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Remover projeto", description = "Exclui um projeto pelo id")
    public void deleteProject (@PathVariable Long id) {
        projectService.delete(id);
    }

    @PutMapping("{id}")
    @Operation(summary = "Atualiza um projeto", description = "Atualiza as informações de um projeto pelo id")
    public Project update (@PathVariable Long id, @RequestBody Project project) {
        return projectService.update(id, project);
    }

    @PutMapping("/updateStatus/{id}")
    @Operation(summary = "Atualizar Status do Projeto", description = "Atualiza o campo de status do projeto")
     public ResponseEntity<Void> updateStatus(@PathVariable Long id,@RequestBody UpdateStatusProjectDTO data) {
            projectService.updateStatusProject(id, data.projectStatus());
            return ResponseEntity.ok().build();
     }


}
