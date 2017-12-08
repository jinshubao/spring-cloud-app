package com.jean.security.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Set;

/**
 * @author jinshubao
 */
@Entity
@DynamicUpdate
@Table(name = "sys_role")
@Inheritance(strategy = InheritanceType.JOINED)
public class SysRole extends BaseEntity {

    private static final long serialVersionUID = 1156216170338502678L;

    @Column(unique = true, nullable = false)
    private String role;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(
            name = "sys_role_authority",
            joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "id"))
    private Set<SysAuthority> authorities;

    @JsonIgnore
    @ManyToMany(mappedBy = "roles", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private Set<SysUser> users;

    public SysRole() {
    }

    public SysRole(Long id) {
        super(id);
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<SysAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<SysAuthority> authorities) {
        this.authorities = authorities;
    }

    public Set<SysUser> getUsers() {
        return users;
    }

    public void setUsers(Set<SysUser> users) {
        this.users = users;
    }

}
