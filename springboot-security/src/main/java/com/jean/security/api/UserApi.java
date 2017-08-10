package com.jean.security.api;

import com.jean.security.model.SysUser;
import com.jean.security.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserApi {

    @Autowired
    SysUserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/add")
    SysUser add(@RequestBody SysUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userService.saveAndFlush(user);
    }


    @GetMapping("/list")
    List<SysUser> list() {
        return userService.findAll();
    }
}
