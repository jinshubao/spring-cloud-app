package com.jean.security.api;

import com.jean.security.entity.SysAuthority;
import com.jean.security.service.SysAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author jinshubao
 */
@RestController
@RequestMapping("/authority")
public class AuthorityApi {

    private final SysAuthorityService authorityService;

    @Autowired
    public AuthorityApi(SysAuthorityService authorityService) {
        this.authorityService = authorityService;
    }

    @PostMapping("/add")
    public SysAuthority add(@RequestBody SysAuthority authority) {
        return authorityService.save(authority);
    }

    @GetMapping("/list")
    public List<SysAuthority> list() {
        return authorityService.findAll();
    }
}
