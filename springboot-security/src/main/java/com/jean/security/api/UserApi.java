package com.jean.security.api;

import com.jean.security.domain.SysUser;
import com.jean.security.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jinshubao
 */
@RestController
@RequestMapping("/user")
public class UserApi extends BaseApi<SysUser> {

    @Autowired
    public UserApi(ISysUserService userService) {
        super(userService);
    }
}
