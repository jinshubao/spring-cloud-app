package com.jean.security.repository;

import com.jean.security.domain.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

/**
 * @author jinshubao
 */
public interface SysRoleRepository extends BaseRepository<SysRole, Long> {

    /**
     * 按用户名查询用户角色
     * @param username 用户名
     * @return 角色列表
     */
    @Query("select r from SysUser u, SysUserRole ur, SysRole r " +
            "where u.id = ur.userId and r.id = ur.roleId and u.username = :username")
    Collection<SysRole> findRoles(@Param("username") String username);
}
