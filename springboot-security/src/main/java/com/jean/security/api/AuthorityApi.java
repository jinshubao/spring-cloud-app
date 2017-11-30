package com.jean.security.api;

import com.jean.security.domain.SysAuthority;
import com.jean.security.service.ISysAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jinshubao
 */
@RestController
@RequestMapping("/authority")
public class AuthorityApi extends BaseApi<SysAuthority> {

    @Autowired
    public AuthorityApi(ISysAuthorityService authorityService) {
        super(authorityService);
    }
}
