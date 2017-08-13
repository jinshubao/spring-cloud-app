package com.jean.security.api;

import com.jean.security.entity.SysResource;
import com.jean.security.service.SysResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public SysResource add(@RequestBody SysResource resource) {
        return resourceService.save(resource);
    }

    @GetMapping("/list")
    public List<SysResource> list() {
        return resourceService.findAll();
    }
}
