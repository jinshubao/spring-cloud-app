package com.jean.auto.service;

import com.jean.auto.entity.Project;
import com.jean.auto.entity.QModule;
import com.jean.auto.entity.QProject;
import com.jean.auto.model.response.ModuleResponse;
import com.jean.auto.model.response.ProjectResponse;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService extends BaseService<Project, Long> implements IProjectService {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @Override
    public List<ProjectResponse> findAllNames() {
  /*      QProject qProject = QProject.project;
        QModule qModule = QModule.module;
        jpaQueryFactory.select(Projections.bean(ProjectResponse.class,qProject.id,qProject.name,qProject.description,qProject.modules))
                .from(qProject, qModule)
                .where( qModule.project.id.eq(qProject.id))
                .fetch();*/

        return repository.findAll().stream().map(project -> {
                    List<ModuleResponse> modules = project.getModules().stream().map(module ->
                            new ModuleResponse(module.getId(), module.getName(), module.getDescription()))
                            .collect(Collectors.toList());
                    return new ProjectResponse(project.getId(), project.getName(), project.getDescription(), modules);
                }
        ).collect(Collectors.toList());
    }
}
