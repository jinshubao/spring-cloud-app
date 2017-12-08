package com.jean.security.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jean.security.model.enums.ResourceType;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Set;

/**
 * @author jinshubao
 */
@Entity
@DynamicUpdate
@Table(name = "sys_resource")
@Inheritance(strategy = InheritanceType.JOINED)
public class SysResource extends BaseEntity {

    private static final long serialVersionUID = -8729591118437365107L;

    /**
     * 资源类型
     */
    @Enumerated(EnumType.STRING)
    private ResourceType type;

    /**
     * 资源, URL、菜单/页面元素标识
     */
    @Column(unique = true, nullable = false, length = 2000)
    private String resource;

    @Column(nullable = false)
    private String method;


    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(
            name = "sys_resource_authority",
            joinColumns = @JoinColumn(name = "resource_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "id"))
    private Set<SysAuthority> authorities;

    public SysResource() {
    }

    public SysResource(Long id) {
        super(id);
    }

    public ResourceType getType() {
        return type;
    }

    public void setType(ResourceType type) {
        this.type = type;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Set<SysAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<SysAuthority> authorities) {
        this.authorities = authorities;
    }
}
