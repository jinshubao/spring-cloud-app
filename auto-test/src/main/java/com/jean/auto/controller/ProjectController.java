package com.jean.auto.controller;

import com.jean.auto.constant.CommonConstant;
import com.jean.auto.entity.Module;
import com.jean.auto.entity.Project;
import com.jean.auto.model.ProjectNameModel;
import com.jean.auto.model.common.ApiSimpleResultHelper;
import com.jean.auto.service.IModuleService;
import com.jean.auto.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/projects", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ProjectController extends BaseController<Project, Long> {

    @Autowired
    private IModuleService moduleService;

    @GetMapping("/modules")
    ApiSimpleResultHelper<List<Module>> modules(@RequestParam(value = "project_id") Long projectId) {
        List<Module> modules = moduleService.findByProjectId(projectId);
        return new ApiSimpleResultHelper<>(CommonConstant.ApiResponse.SUCCESS, modules);
    }

    @GetMapping("/names")
    ApiSimpleResultHelper<List<ProjectNameModel>> names() {
        List<ProjectNameModel> modules = ((IProjectService) baseService).findAllProjectNames();
        return new ApiSimpleResultHelper<>(CommonConstant.ApiResponse.SUCCESS, modules);
    }

    @PutMapping
    ApiSimpleResultHelper<Project> modify(@RequestBody Project object) {
        object.setModifiedTime(new Date());
        object.setEnabled(true);
        Project o = baseService.save(object);
        return new ApiSimpleResultHelper<>(CommonConstant.ApiResponse.SUCCESS, o);
    }

}
