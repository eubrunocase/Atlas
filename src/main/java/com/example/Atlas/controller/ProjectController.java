package com.example.Atlas.controller;

import com.example.Atlas.model.Project;
import com.example.Atlas.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/atlas/project")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping
    public ResponseEntity<Project> createProject(@RequestBody Project project) {
        System.out.println("recebendo projeto no controller" + project);
        Project savedProject = projectService.save(project);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProject);
    }

    @GetMapping
    public List<Project> getAll () {
        return projectService.findAll();
    }

    @DeleteMapping("{id}")
    public void deleteProject (@PathVariable Long id) {
        projectService.delete(id);
    }

    @PutMapping("{id}")
    public Project update (@PathVariable Long id, @RequestBody Project project) {
        return projectService.update(id, project);
    }

}
