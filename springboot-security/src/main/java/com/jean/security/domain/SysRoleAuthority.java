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
@Table(name = "sys_role_authority", uniqueConstraints = {@UniqueConstraint(columnNames = {"roleId", "authorityId"})})
public class SysRoleAuthority {

    private static final long serialVersionUID = -5849801827998416871L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long roleId;

    private Long authorityId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(Long authorityId) {
        this.authorityId = authorityId;
    }
}
