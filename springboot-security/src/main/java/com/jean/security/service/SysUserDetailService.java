package com.jean.security.service;

import com.jean.security.entity.SysRole;
import com.jean.security.entity.SysUser;
import com.jean.security.repository.SysUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;


@Service
public class SysUserDetailService implements UserDetailsService {

    private final SysUserRepository sysUserRepository;

    @Autowired
    public SysUserDetailService(SysUserRepository sysUserRepository) {
        this.sysUserRepository = sysUserRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = sysUserRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        Set<GrantedAuthority> authorities = new HashSet<>();
        for (SysRole role : user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(),
                user.isEnabled(),
                user.isAccountNonExpired(),
                user.isCredentialsNonExpired(),
                user.isAccountNonLocked()
                , authorities);
    }
}
