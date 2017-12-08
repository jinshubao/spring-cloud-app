package com.jean.user.api.controller;

import com.jean.user.api.constant.CommonConstant;
import com.jean.user.api.entity.Project;
import com.jean.user.api.model.common.ApiSimpleResultHelper;
import com.jean.user.api.model.request.AddProjectRequest;
import com.jean.user.api.model.response.ProjectResponse;
import com.jean.user.api.service.IProjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/projects", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ProjectController extends BaseController<Project, Long> {

    @GetMapping("/names")
    ApiSimpleResultHelper<List<ProjectResponse>> names() {
        List<ProjectResponse> modules = ((IProjectService) baseService).findAllNames();
        return new ApiSimpleResultHelper<>(CommonConstant.ApiResponse.SUCCESS, modules);
    }

    @PostMapping
    ApiSimpleResultHelper<Project> add(@RequestBody @Valid AddProjectRequest request) {
        Project object = new Project();
        BeanUtils.copyProperties(request, object);
        return super.add(object);
    }
}
