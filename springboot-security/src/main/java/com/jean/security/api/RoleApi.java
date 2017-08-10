package com.jean.security.api;

import com.jean.security.model.SysRole;
import com.jean.security.model.dto.RoleDto;
import com.jean.security.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleApi {

    private final SysRoleService roleService;

    @Autowired
    public RoleApi(SysRoleService roleService) {
        this.roleService = roleService;
    }


    @PostMapping("/add")
    public RoleDto add(@RequestBody SysRole role) {
        SysRole r = roleService.saveAndFlush(role);
        return new RoleDto(r);
    }

    @GetMapping("/list")
    public List<RoleDto> list() {
        List<SysRole> roles = roleService.findAll();
        List<RoleDto> list = new ArrayList<>();
        for (SysRole role : roles) {
            list.add(new RoleDto(role));
        }
        return list;
    }
}
