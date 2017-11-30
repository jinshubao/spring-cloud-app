package com.jean.security.domain;

import com.jean.security.model.enums.ResourceType;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * @author jinshubao
 */
@Entity
@DynamicUpdate
@Table(name = "sys_resource")
@Inheritance(strategy = InheritanceType.JOINED)
public class SysResource extends BaseEntity {

    private static final long serialVersionUID = -8729591118437365107L;

    /**
     * 资源类型
     */
    @Enumerated(EnumType.STRING)
    private ResourceType type;

    /**
     * 资源, URL、菜单/页面元素标识
     */
    @Column(length = 2000)
    private String resource;

    private String method;

    public ResourceType getType() {
        return type;
    }

    public void setType(ResourceType type) {
        this.type = type;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
