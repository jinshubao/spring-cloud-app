package com.jean.security.service;

import com.jean.security.domain.SysResource;
import com.jean.security.model.dto.AuthorityDto;
import com.jean.security.model.dto.ResourceDto;

import java.util.Collection;

/**
 * @author jinshubao
 */
public interface ISysResourceService extends IBaseService<SysResource> {

    /**
     *
     * @param resource
     * @return
     */
    Collection<String> findAuthorities(String method, String resource);

    /**
     *
     * @return
     */
    Collection<ResourceDto> findAllResources();
}
