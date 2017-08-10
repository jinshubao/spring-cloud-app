package com.jean.security.api;

import com.jean.security.model.SysAuthority;
import com.jean.security.model.dto.AuthorityDto;
import com.jean.security.repository.SysAuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/authority")
public class AuthorityApi {

    private final SysAuthorityRepository authorityRepository;

    @Autowired
    public AuthorityApi(SysAuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    @PostMapping("/add")
    public AuthorityDto add(@RequestBody SysAuthority authority) {
        SysAuthority a = authorityRepository.saveAndFlush(authority);
        return new AuthorityDto(a);
    }

    @GetMapping("/list")
    public List<AuthorityDto> list() {
        List<SysAuthority> authorities = authorityRepository.findAll();
        ArrayList<AuthorityDto> list = new ArrayList<>();
        for (SysAuthority authority : authorities) {
            list.add(new AuthorityDto(authority));
        }
        return list;
    }
}
