package com.jean.security.domain;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * @author jinshubao
 */
@Entity
@DynamicUpdate
@Table(name = "sys_authority")
@Inheritance(strategy = InheritanceType.JOINED)
public class SysAuthority extends BaseEntity {

    private static final long serialVersionUID = -586933872657627926L;

    @Column(unique = true, nullable = false)
    private String authority;

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
