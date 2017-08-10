package com.jean.security.model.dto;

import com.jean.security.model.SysRole;

import java.io.Serializable;

public class RoleDto implements Serializable {

    private String name;

    private String description;

    public RoleDto() {
    }

    public RoleDto(SysRole role) {
        this.name = role.getName();
        this.description = role.getDescription();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
