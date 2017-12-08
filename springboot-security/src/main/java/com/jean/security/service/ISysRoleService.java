package com.jean.security.service;

import com.jean.security.domain.SysRole;

import java.util.Collection;
import java.util.Set;

/**
 * @author jinshubao
 */
public interface ISysRoleService extends IBaseService<SysRole> {

    /**
     * 按角色名查询角色
     *
     * @param roleNames 角色名
     * @return 角色集合
     */
    Collection<SysRole> findByNames(Set<String> roleNames);
}
