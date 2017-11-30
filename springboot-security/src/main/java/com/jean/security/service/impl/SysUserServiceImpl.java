package com.jean.security.service.impl;

import com.jean.security.domain.SysAuthority;
import com.jean.security.domain.SysRole;
import com.jean.security.domain.SysUser;
import com.jean.security.repository.SysAuthorityRepository;
import com.jean.security.repository.SysRoleRepository;
import com.jean.security.repository.SysUserRepository;
import com.jean.security.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author jinshubao
 */
@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUser> implements ISysUserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SysAuthorityRepository authorityRepository;

    @Autowired
    private SysRoleRepository roleRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SysUser save(SysUser sysUser) {
        sysUser.setPassword(passwordEncoder.encode(sysUser.getPassword()));
        return super.save(sysUser);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = ((SysUserRepository) repository).findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }

        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        Collection<SysRole> roles = roleRepository.findRoles(username);
        for (SysRole role : roles) {
            Collection<SysAuthority> auths = authorityRepository.findSysAuthorities(role.getId());
            authorities.addAll(auths.stream().map(auth -> new SimpleGrantedAuthority(auth.getAuthority())).collect(Collectors.toSet()));
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(),
                user.isEnabled(),
                user.isAccountNonExpired(),
                user.isCredentialsNonExpired(),
                user.isAccountNonLocked(),
                authorities);
    }
}
