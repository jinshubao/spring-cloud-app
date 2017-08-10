package com.jean.security.model.dto;

import com.jean.security.model.SysAuthority;
import com.jean.security.model.SysRole;

import java.io.Serializable;

public class AuthorityDto implements Serializable {


    private String name;

    private String description;

    public AuthorityDto() {
    }

    public AuthorityDto(SysAuthority authority) {
        this.name = authority.getName();
        this.description = authority.getDescription();
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
