package com.jean.auto.controller;

import com.jean.auto.constant.CommonConstant;
import com.jean.auto.entity.Module;
import com.jean.auto.entity.Project;
import com.jean.auto.model.common.ApiSimpleResultHelper;
import com.jean.auto.model.request.AddModuleRequest;
import com.jean.auto.service.IProjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
}
