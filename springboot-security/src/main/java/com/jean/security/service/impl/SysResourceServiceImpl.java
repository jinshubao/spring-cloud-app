package com.jean.security.service.impl;

import com.jean.security.domain.SysAuthority;
import com.jean.security.domain.SysResource;
import com.jean.security.model.dto.ResourceDto;
import com.jean.security.repository.SysAuthorityRepository;
import com.jean.security.repository.SysResourceRepository;
import com.jean.security.service.ISysResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author jinshubao
 */
@Service
public class SysResourceServiceImpl extends BaseServiceImpl<SysResource> implements ISysResourceService {

    @Cacheable(value = "auth", key = "'auth:'+#method+':'+#resource")
    @Override
    public Collection<String> findAuthorities(String method, String resource) {
        SysResource sysResource = ((SysResourceRepository) repository).findByResourceAndMethod(resource, method);
        Collection<SysAuthority> authorities = authorityRepository.findByResourcesIn(new SysResource(sysResource.getId()));
        return authorities.stream().map(SysAuthority::getAuthority).collect(Collectors.toSet());
    }

    @CacheEvict(value = "auth", key = "'auth:'+#sysResource.method+':'+#sysResource.resource")
    @Transactional(rollbackFor = Exception.class)
    @Override
    public SysResource update(SysResource sysResource) {
        return super.update(sysResource);
    }


    @Autowired
    SysAuthorityRepository authorityRepository;

    @Cacheable(value = "auth", key = "'auth:all'")
    @Override
    public Collection<ResourceDto> findAllResources() {
        Collection<ResourceDto> res = new HashSet<>();
        List<SysResource> sysResources = repository.findAll();
        for (SysResource sysResource : sysResources) {
            Collection<SysAuthority> sysAuthorities = authorityRepository.findByResourcesIn(new SysResource(sysResource.getId()));
            for (SysAuthority sysAuthority : sysAuthorities) {
                res.add(new ResourceDto(sysResource.getResource(), sysResource.getMethod(), sysAuthority.getAuthority()));
            }
        }
        return res;
    }
}
