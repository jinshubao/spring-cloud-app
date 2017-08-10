package com.jean.security.model;

import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class SysUserDetail extends SysUser implements UserDetails {

    private Set<GrantedAuthority> authorities = new HashSet<>();

    public SysUserDetail(SysUser user, Set<GrantedAuthority> authorities) {
        BeanUtils.copyProperties(user, this, "roles");
        this.authorities = authorities;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}
