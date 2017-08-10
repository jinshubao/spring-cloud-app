package com.jean.security.api;

import com.jean.security.model.SysAuthority;
import com.jean.security.repository.SysAuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authority")
public class AuthorityApi {

    @Autowired
    SysAuthorityRepository authorityRepository;

    @PostMapping("/add")
    SysAuthority add(@RequestBody SysAuthority authority) {
        return authorityRepository.saveAndFlush(authority);
    }

    @GetMapping("/list")
    List<SysAuthority> list() {
        return authorityRepository.findAll();
    }
}
