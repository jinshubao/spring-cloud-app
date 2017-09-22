package com.jean.auto.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@MappedSuperclass
@JsonIgnoreProperties({"hibernateLazyInitializer", "new"})
public abstract class BaseEntity extends AbstractPersistable<Long> {

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    protected Date createdTime;

    @Temporal(TemporalType.TIMESTAMP)
    protected Date modifiedTime;

    @Column(length = 500)
    protected String description;

    protected boolean enabled;

    @Column(length = 100)
    protected String name;


    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
