package com.jean.security.api;

import com.jean.security.entity.SysUser;
import com.jean.security.service.SysRoleService;
import com.jean.security.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserApi {

    private final SysUserService userService;

    private final PasswordEncoder passwordEncoder;

    private final SysRoleService roleService;

    @Autowired
    public UserApi(SysUserService userService, PasswordEncoder passwordEncoder, SysRoleService roleService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    @PostMapping("/add")
    public SysUser add(@RequestBody SysUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userService.save(user);
    }

    @GetMapping("/list")
    public List<SysUser> list() {
        return userService.findAll();
    }
}
