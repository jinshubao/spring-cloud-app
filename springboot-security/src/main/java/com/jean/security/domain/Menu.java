package com.jean.security.domain;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Set;

/**
 * 菜单/按钮
 *
 * @author jinshubao
 * @since 1.0
 */
@Entity
@DynamicUpdate
@Table(name = "sys_menu")
@Inheritance(strategy = InheritanceType.JOINED)
public class Menu extends BaseEntity {

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "parent_id")
    private Menu parent;

    @OneToMany(mappedBy = "parent")
    private Set<Menu> subMenus;

    private String menu;

    public Menu getParent() {
        return parent;
    }

    public void setParent(Menu parent) {
        this.parent = parent;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }
}
