package com.jean.security.entity;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "sys_authority")
@DynamicUpdate
@Inheritance(strategy = InheritanceType.JOINED)
public class SysAuthority extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "sys_authority_resource",
            joinColumns = {@JoinColumn(name = "authority_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "resource_id", referencedColumnName = "id")})
    private Set<SysResource> resources;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<SysResource> getResources() {
        return resources;
    }

    public void setResources(Set<SysResource> resources) {
        this.resources = resources;
    }
}
