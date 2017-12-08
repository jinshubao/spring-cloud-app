package com.jean.security.service.impl;

import com.jean.security.domain.SysRole;
import com.jean.security.repository.SysRoleRepository;
import com.jean.security.service.ISysRoleService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;

/**
 * @author jinshubao
 */
@Service
public class SysRoleServiceImpl extends BaseServiceImpl<SysRole> implements ISysRoleService {

    @Override
    public Collection<SysRole> findByNames(Set<String> roleNames) {
        return ((SysRoleRepository)repository).findByNameIn(roleNames);
    }
}
