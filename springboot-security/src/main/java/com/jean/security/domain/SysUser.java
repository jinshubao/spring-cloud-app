package com.jean.security.domain;


import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Set;

/**
 * @author jinshubao
 */
@Entity
@Table(name = "sys_user")
@DynamicUpdate
@Inheritance(strategy = InheritanceType.JOINED)
public class SysUser extends BaseEntity {

    private static final long serialVersionUID = -7667155827375881933L;

    private String realName;

    @Column(nullable = false)
    private String password;

    private String email;

    /**
     * 账户未过期
     */
    @Column(nullable = false)
    private Boolean accountNonExpired;

    /**
     * 账户未锁定
     */
    @Column(nullable = false)
    private Boolean accountNonLocked;

    /**
     * 凭证未过期
     */
    @Column(nullable = false)
    private Boolean credentialsNonExpired;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(
            name = "sys_user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<SysRole> roles;

    public SysUser() {
    }

    public SysUser(Long id) {
        super(id);
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
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

    public Boolean getAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(Boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public Boolean getAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(Boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public Boolean getCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public Set<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<SysRole> roles) {
        this.roles = roles;
    }
}
