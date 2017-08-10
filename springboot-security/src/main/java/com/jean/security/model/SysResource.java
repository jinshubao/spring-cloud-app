package com.jean.security.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "sys_resource")
public class SysResource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", length = 10)
    private Integer id;

    @Column(name = "url", length = 1000)
    private String url;

    @Column(name = "name", length = 200)
    private String name;

    @Column(name = "description", length = 400)
    private String description;

    @Column(name = "method", length = 20)
    private String method;

    @Column(name = "enable", nullable = false)
    private Boolean enable;

    //@ManyToMany注释表示SysResource是多对多关系的一端。
    //@JoinTable描述了多对多关系的数据表关系。name属性指定中间表名称，joinColumns定义中间表与SysResource表的外键关系。
    //中间表sys_authority_resource的resource_id列是SysResource表的主键列对应的外键列，inverseJoinColumns属性定义了中间表与另外一端(SysAuthority)的外键关系。
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "sys_authority_resource",
            joinColumns = {@JoinColumn(name = "resource_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_id", referencedColumnName = "id")})
    private Set<SysAuthority> authorities;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public Set<SysAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<SysAuthority> authorities) {
        this.authorities = authorities;
    }
}
