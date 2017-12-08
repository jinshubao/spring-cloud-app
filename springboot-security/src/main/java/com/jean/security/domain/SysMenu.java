package com.jean.security.domain;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Set;

/**
 * 菜单
 *
 * @author jinshubao
 * @since 1.0
 */
@Entity
@DynamicUpdate
@Table(name = "sys_menu")
@Inheritance(strategy = InheritanceType.JOINED)
public class SysMenu extends BaseEntity {

    private String code;

    @ManyToOne(cascade = CascadeType.REFRESH)
    private SysMenu parentMenu;

    @OneToMany(mappedBy = "parentMenu", cascade = CascadeType.REFRESH)
    private Set<SysMenu> submenus;

    public SysMenu() {
    }

    public SysMenu(Long id) {
        super(id);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public SysMenu getParentMenu() {
        return parentMenu;
    }

    public void setParentMenu(SysMenu parentMenu) {
        this.parentMenu = parentMenu;
    }

    public Set<SysMenu> getSubmenus() {
        return submenus;
    }

    public void setSubmenus(Set<SysMenu> submenus) {
        this.submenus = submenus;
    }
}
