package com.jean.security.api;

import com.jean.security.domain.SysRole;
import com.jean.security.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jinshubao
 */
@RestController
@RequestMapping("/role")
public class RoleApi extends BaseApi<SysRole> {

    @Autowired
    public RoleApi(ISysRoleService roleService) {
        super(roleService);
    }
}
