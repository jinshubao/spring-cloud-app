package com.jean.security.api;

import com.jean.security.model.SysResource;
import com.jean.security.model.dto.ResourceDto;
import com.jean.security.service.SysResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/resource")
public class ResourceApi {

    private final SysResourceService resourceService;

    @Autowired
    public ResourceApi(SysResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @PostMapping("/add")
    public ResourceDto add(@RequestBody SysResource resource) {
        SysResource r = resourceService.saveAndFlush(resource);

        return new ResourceDto(r);
    }

    @GetMapping("/list")
    public List<ResourceDto> list() {
        List<SysResource> resources = resourceService.findAll();
        List<ResourceDto> list = new ArrayList<>();
        for (SysResource resource : resources) {
            list.add(new ResourceDto(resource));
        }
        return list;

    }
}
