package com.jean.security.api;

import com.jean.security.model.SysRole;
import com.jean.security.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleApi {

    @Autowired
    SysRoleService roleService;


    @PostMapping("/add")
    SysRole add(@RequestBody SysRole role) {
        return roleService.saveAndFlush(role);
    }

    @GetMapping("/list")
    List<SysRole> list() {
        return roleService.findAll();
    }
}
