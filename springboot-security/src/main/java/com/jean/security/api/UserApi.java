package com.jean.security.api;

import com.jean.security.model.SysUser;
import com.jean.security.model.dto.UserDto;
import com.jean.security.service.SysRoleService;
import com.jean.security.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    public UserDto add(@RequestBody SysUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        SysUser usr = userService.saveAndFlush(user);
        return new UserDto(usr);
    }

    @GetMapping("/list")
    public List<UserDto> list() {

        List<SysUser> users = userService.findAll();
        List<UserDto> list = new ArrayList();
        for (SysUser user : users) {
            list.add(new UserDto(user));
        }
        return list;
    }
}
