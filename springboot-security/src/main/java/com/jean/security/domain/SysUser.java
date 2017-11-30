package com.jean.security.domain;


import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;

/**
 * @author jinshubao
 */
@Entity
@Table(name = "sys_user")
@DynamicUpdate
@Inheritance(strategy = InheritanceType.JOINED)
public class SysUser extends BaseEntity {

    private static final long serialVersionUID = -7667155827375881933L;

    @NotBlank
    @Column(unique = true, nullable = false, updatable = false)
    private String username;

    @NotBlank
    @Column(nullable = false)
    private String password;

    private String email;

    /**
     * 账户未过期
     */
    @Column(nullable=false)
    private Boolean accountNonExpired;

    /**
     * 账户未锁定
     */
    @Column(nullable=false)
    private Boolean accountNonLocked;

    /**
     * 凭证未过期
     */
    @Column(nullable=false)
    private Boolean credentialsNonExpired;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }
}
