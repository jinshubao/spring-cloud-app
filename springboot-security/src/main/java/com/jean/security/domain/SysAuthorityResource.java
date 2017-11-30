package com.jean.security.domain;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * TODO
 *
 * @author jinshubao
 * @since TODO
 */
@Entity
@DynamicUpdate
@Table(name = "sys_authority_resource", uniqueConstraints = {@UniqueConstraint(columnNames = {"authorityId", "resourceId"})})
public class SysAuthorityResource {

    private static final long serialVersionUID = 6790976418780251390L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long authorityId;

    private Long resourceId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(Long authorityId) {
        this.authorityId = authorityId;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }
}
