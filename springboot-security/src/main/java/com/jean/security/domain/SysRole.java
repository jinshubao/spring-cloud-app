package com.jean.security.domain;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * @author jinshubao
 */
@Entity
@DynamicUpdate
@Table(name = "sys_role")
@Inheritance(strategy = InheritanceType.JOINED)
public class SysRole extends BaseEntity {

    private static final long serialVersionUID = 1156216170338502678L;

    @Column(unique = true, nullable = false)
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
