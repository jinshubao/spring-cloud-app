package com.jean.security.domain;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;

/**
 * TODO
 *
 * @author jinshubao
 * @since TODO
 */
@Entity
@DynamicUpdate
@Table(name = "sys_user_role", uniqueConstraints = {@UniqueConstraint(columnNames = {"userId", "roleId"})})
@Inheritance(strategy = InheritanceType.JOINED)
public class SysUserRole extends AbstractPersistable<Long> {
    private static final long serialVersionUID = -8544307253881717705L;

    private Long userId;

    private Long roleId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
