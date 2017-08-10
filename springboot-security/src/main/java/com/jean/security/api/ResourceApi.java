package com.jean.security.api;

import com.jean.security.model.SysResource;
import com.jean.security.service.SysResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/resource")
public class ResourceApi {

    @Autowired
    SysResourceService resourceService;

    @PostMapping("/add")
    SysResource add(@RequestBody SysResource resource) {
        return resourceService.saveAndFlush(resource);
    }

    @GetMapping("/list")
    List<SysResource> list() {
        return resourceService.findAll();
    }
}
