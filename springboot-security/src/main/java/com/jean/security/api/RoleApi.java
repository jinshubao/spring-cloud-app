package com.jean.security.api;

import com.jean.security.entity.SysRole;
import com.jean.security.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author jinshubao
 */
@RestController
@RequestMapping("/role")
public class RoleApi {

    private final SysRoleService roleService;

    @Autowired
    public RoleApi(SysRoleService roleService) {
        this.roleService = roleService;
    }


    @PostMapping("/add")
    public SysRole add(@RequestBody SysRole role) {
        return roleService.save(role);
    }

    @GetMapping("/list")
    public List<SysRole> list() {
        return roleService.findAll();
    }
}
