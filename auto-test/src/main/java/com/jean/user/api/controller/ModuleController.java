package com.jean.user.api.controller;

import com.jean.user.api.constant.CommonConstant;
import com.jean.user.api.entity.Module;
import com.jean.user.api.entity.Project;
import com.jean.user.api.model.common.ApiSimpleResultHelper;
import com.jean.user.api.model.request.AddModuleRequest;
import com.jean.user.api.model.response.ModuleResponse;
import com.jean.user.api.service.IModuleService;
import com.jean.user.api.service.IProjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/modules", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ModuleController extends BaseController<Module, Long> {

    @Autowired
    private IProjectService projectService;

    @PostMapping
    ApiSimpleResultHelper<Module> add(@RequestBody @Valid AddModuleRequest request) {
        Project project = projectService.findOne(request.getProjectId());
        if (project == null) {
            return new ApiSimpleResultHelper<>(CommonConstant.ApiResponse.PARAMETER_ERROR, null);
        }
        Module object = new Module();
        BeanUtils.copyProperties(request, object);
        object.setProject(project);
        return super.add(object);
    }

    @GetMapping("/names")
    ApiSimpleResultHelper<List<ModuleResponse>> names(@RequestParam(value = "project_id", required = false) Long projectId) {
        List<ModuleResponse> modules = ((IModuleService) baseService).findAllNames(projectId);
        return new ApiSimpleResultHelper<>(CommonConstant.ApiResponse.SUCCESS, modules);
    }
}
