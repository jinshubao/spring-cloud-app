package com.jean.security.repository;

import com.jean.security.domain.SysAuthority;
import com.jean.security.domain.SysResource;
import com.jean.security.domain.SysRole;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

/**
 * @author jinshubao
 */
public interface SysAuthorityRepository extends BaseRepository<SysAuthority, Long> {

    /**
     * 按角色查询权限列表
     *
     * @param roleId 角色ID
     * @return 权限列表
     */
    /*@Query("select a from SysAuthority a, SysRoleAuthority ra, SysRole r " +
            "where a.id = ra.authorityId and r.id = ra.roleId and r.id = :roleId")
    Collection<SysAuthority> findSysAuthorities(@Param("roleId") Long roleId);*/


//    Collection<SysAuthority> findByRolesIn(SysRole role);

    Collection<SysAuthority> findByResourcesIn(SysResource resource);

    Collection<SysAuthority> findByRolesIn(SysRole role);
}
