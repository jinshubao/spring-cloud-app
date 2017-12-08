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
@Table(name = "sys_authority")
@Inheritance(strategy = InheritanceType.JOINED)
public class SysAuthority extends BaseEntity {

    private static final long serialVersionUID = -586933872657627926L;

    @Column(unique = true, nullable = false)
    private String authority;

    @JsonIgnore
    @ManyToMany(mappedBy = "authorities", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private Set<SysResource> resources;

    @JsonIgnore
    @ManyToMany(mappedBy = "authorities", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private Set<SysRole> roles;

    public SysAuthority() {
    }

    public SysAuthority(Long id) {
        super(id);
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public Set<SysResource> getResources() {
        return resources;
    }

    public void setResources(Set<SysResource> resources) {
        this.resources = resources;
    }

    public Set<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<SysRole> roles) {
        this.roles = roles;
    }


}
