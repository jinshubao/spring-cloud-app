package com.jean.user.entity;


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
    protected String remark;

    @Column(length = 500)
    protected String description;

    protected boolean enabled;


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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
}
