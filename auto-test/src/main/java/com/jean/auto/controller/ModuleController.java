package com.jean.auto.controller;

import com.jean.auto.constant.CommonConstant;
import com.jean.auto.entity.Api;
import com.jean.auto.entity.Module;
import com.jean.auto.entity.Project;
import com.jean.auto.model.AddModuleRequest;
import com.jean.auto.model.common.ApiSimpleResultHelper;
import com.jean.auto.service.IApiService;
import com.jean.auto.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/modules", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ModuleController extends BaseController<Module, Long> {

    @Autowired
    private IApiService apiService;

    @Autowired
    private IProjectService projectService;

    @PostMapping
    ApiSimpleResultHelper<Module> add(@RequestBody @Valid AddModuleRequest request) {
        Module object = new Module();
        object.setCreatedTime(new Date());
        object.setModifiedTime(object.getCreatedTime());
        object.setEnabled(true);
        object.setName(request.getName());
        object.setRemark(request.getRemark());
        object.setDescription(request.getDescription());
        Project project = projectService.findOne(request.getProjectId());
        object.setProject(project);
        Module o = baseService.save(object);
        return new ApiSimpleResultHelper<>(CommonConstant.ApiResponse.SUCCESS, o);
    }

    @GetMapping("/apis")
    ApiSimpleResultHelper<List<Api>> apis(@RequestParam(value = "module_id") Long moduleId) {
        List<Api> apis = apiService.findByModuleId(moduleId);
        return new ApiSimpleResultHelper<>(CommonConstant.ApiResponse.SUCCESS, apis);
    }

}
