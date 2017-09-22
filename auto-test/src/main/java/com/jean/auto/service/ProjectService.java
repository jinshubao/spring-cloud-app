package com.jean.auto.service;

import com.jean.auto.entity.Project;
import com.jean.auto.model.response.ModuleResponse;
import com.jean.auto.model.response.ProjectResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService extends BaseService<Project, Long> implements IProjectService {

    @Override
    public List<ProjectResponse> findAllProjectNames() {
        return repository.findAll().stream().map(project -> {
                    List<ModuleResponse> modules = project.getModules().stream().map(module ->
                            new ModuleResponse(module.getId(), module.getName(), module.getDescription()))
                            .collect(Collectors.toList());
                    return new ProjectResponse(project.getId(), project.getName(), project.getDescription(), modules);
                }
        ).collect(Collectors.toList());
    }
}
