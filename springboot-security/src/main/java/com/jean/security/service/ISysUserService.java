package com.jean.security.service;

import com.jean.security.domain.SysUser;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Set;

/**
 * @author jinshubao
 */
public interface ISysUserService extends IBaseService<SysUser>, UserDetailsService {

    /**
     * 给用户添加/删除角色
     * @param userId 用户ID
     * @param roleNames 角色名
     * @return 用户
     */
    SysUser updateRole(Long userId, Set<String> roleNames);
}
