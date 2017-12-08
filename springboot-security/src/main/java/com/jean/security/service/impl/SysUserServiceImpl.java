package com.jean.security.service.impl;

import com.jean.security.domain.SysAuthority;
import com.jean.security.domain.SysRole;
import com.jean.security.domain.SysUser;
import com.jean.security.exception.UserNotFoundException;
import com.jean.security.model.dto.MyUserDetails;
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
import java.util.Collections;
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

    @Autowired
    private SysUserRepository userRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SysUser save(SysUser sysUser) {
        sysUser.setPassword(passwordEncoder.encode(sysUser.getPassword()));
        return super.save(sysUser);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = userRepository.findByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        Collection<SysRole> sysRoles = roleRepository.findByUsersIn(new SysUser(user.getId()));
        for (SysRole userRole : sysRoles) {
            Collection<SysAuthority> sysAuthorities = authorityRepository.findByRolesIn(new SysRole(userRole.getId()));
            authorities.addAll(sysAuthorities.stream().map(auth -> new SimpleGrantedAuthority(auth.getAuthority())).collect(Collectors.toSet()));
        }
        MyUserDetails userDetails = new MyUserDetails();
        userDetails.setUsername(user.getName());
        userDetails.setPassword(user.getPassword());
        userDetails.setEnabled(user.isEnabled());
        userDetails.setAccountNonExpired(user.getAccountNonExpired());
        userDetails.setCredentialsNonExpired(user.getCredentialsNonExpired());
        userDetails.setAccountNonLocked(user.getAccountNonLocked());
        userDetails.setAuthorities(authorities);
        userDetails.setEnabled(user.isEnabled());
        return userDetails;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SysUser updateRole(Long userId, Set<String> roleNames) {
        SysUser sysUser = userRepository.findOne(userId);
        if (sysUser == null) {
            throw new UserNotFoundException("用户不存在");
        }
        Collection<SysRole> sysRoles = roleRepository.findByNameIn(roleNames);
        sysUser.setRoles(new HashSet<>(sysRoles));
        return userRepository.saveAndFlush(sysUser);
    }
}
