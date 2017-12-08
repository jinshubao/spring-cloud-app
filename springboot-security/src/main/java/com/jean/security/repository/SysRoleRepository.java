package com.jean.security.repository;

import com.jean.security.domain.SysRole;
import com.jean.security.domain.SysUser;

import java.util.Collection;
import java.util.Set;

/**
 * @author jinshubao
 */
public interface SysRoleRepository extends BaseRepository<SysRole, Long> {

    /**
     * 按用户查询角色
     *
     * @param user 用户
     * @return 角色集合
     */
    Collection<SysRole> findByUsersIn(SysUser user);

    /**
     * 按角色名查询角色
     *
     * @param roleNames 角色名
     * @return 角色集合
     */
    Collection<SysRole> findByNameIn(Set<String> roleNames);

}
