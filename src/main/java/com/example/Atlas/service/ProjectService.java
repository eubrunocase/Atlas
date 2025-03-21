package com.example.Atlas.service;

import com.example.Atlas.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class ProjectService extends BaseService<Project> {

    public ProjectService(JpaRepository<Project, Long> repository) {
        super(repository);
    }

}
