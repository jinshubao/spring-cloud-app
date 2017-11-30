package com.jean.security.service.impl;

import com.jean.security.domain.SysResource;
import com.jean.security.model.dto.ResourceDto;
import com.jean.security.repository.SysResourceRepository;
import com.jean.security.service.ISysResourceService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * @author jinshubao
 */
@Service
public class SysResourceServiceImpl extends BaseServiceImpl<SysResource> implements ISysResourceService {

    @Cacheable(value = "auth", key = "'auth:'+#method+':'+#resource")
    @Override
    public Collection<String> findAuthorities(String method, String resource) {
        return ((SysResourceRepository) repository).findAuthorities(method, resource);
    }

    @CacheEvict(value = "auth", key = "'auth:'+#sysResource.method+':'+#sysResource.resource")
    @Transactional(rollbackFor = Exception.class)
    @Override
    public SysResource update(SysResource sysResource) {
        return super.update(sysResource);
    }

    @Cacheable(value = "auth", key = "'auth:all'")
    @Override
    public Collection<ResourceDto> findAllResources() {
        return ((SysResourceRepository) repository).findAllResources();
    }
}
