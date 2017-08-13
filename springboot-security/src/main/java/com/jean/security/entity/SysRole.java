package com.jean.security.entity;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "sys_role")
@DynamicUpdate
@Inheritance(strategy = InheritanceType.JOINED)
public class SysRole extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinTable(name = "sys_role_authority",
            joinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_id", referencedColumnName = "id")})
    private Set<SysAuthority> authorities;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<SysAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<SysAuthority> authorities) {
        this.authorities = authorities;
    }
}
