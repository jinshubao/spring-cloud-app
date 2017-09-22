package com.jean.auto.service;

import com.jean.auto.entity.Project;
import com.jean.auto.model.response.ProjectResponse;

import java.util.List;

public interface IProjectService extends IBaseService<Project, Long> {

    List<ProjectResponse> findAllProjectNames();

}
