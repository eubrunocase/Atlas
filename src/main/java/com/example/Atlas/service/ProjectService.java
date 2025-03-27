package com.example.Atlas.service;

import com.example.Atlas.model.Project;
import com.example.Atlas.model.enums.ProjectStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class ProjectService extends BaseService<Project> {

    public ProjectService(JpaRepository<Project, Long> repository) {
        super(repository);
    }

    public Project createProject(@RequestBody Project project) {
        project.setStatus(ProjectStatus.AGUARDANDO_ANALISE_PRELIMINAR);
        return super.save(project);
    }


}
