package com.jean.security.repository;

import com.jean.security.domain.SysResource;
import com.jean.security.model.dto.ResourceDto;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

/**
 * @author jinshubao
 */
public interface SysResourceRepository extends BaseRepository<SysResource, Long> {
    /**
     * 按资源查询所需权限
     *
     * @param resource 资源
     * @return 权限列表
     */
    @Query("select distinct a.authority " +
            "from SysResource r, SysAuthorityResource ra, SysAuthority a " +
            "where r.method = ?1 and r.resource = ?2 and r.id = ra.resourceId and a.id = ra.authorityId ")
    Collection<String> findAuthorities(String method, String resource);

    /**
     * 查询所有资源
     *
     * @return 资源列表
     */
    @Query("select new com.jean.security.model.dto.ResourceDto(r.resource, r.method, a.authority) " +
            "from SysResource r, SysAuthorityResource ra, SysAuthority a " +
            "where r.id = ra.resourceId and a.id = ra.authorityId")
    Collection<ResourceDto> findAllResources();
}
