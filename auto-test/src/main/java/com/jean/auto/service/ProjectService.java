package com.jean.auto.service;

import com.jean.auto.entity.Project;
import com.jean.auto.model.ProjectNameModel;
import com.jean.auto.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService extends BaseService<Project, Long> implements IProjectService {

    @Override
    public List<ProjectNameModel> findAllProjectNames() {
        return ((ProjectRepository)repository).findAllProjectNames();
    }
}
