package com.jean.security.entity;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "sys_resource")
@DynamicUpdate
@Inheritance(strategy = InheritanceType.JOINED)
public class SysResource extends BaseEntity {

    @Column(length = 1000)
    private String url;

    private String name;

    private String method;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
}
