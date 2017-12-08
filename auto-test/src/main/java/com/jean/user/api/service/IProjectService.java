package com.jean.user.api.service;

import com.jean.user.api.entity.Project;
import com.jean.user.api.model.response.ProjectResponse;

import java.util.List;

public interface IProjectService extends IBaseService<Project, Long> {

    List<ProjectResponse> findAllNames();

}
