package com.jean.security.model.dto;

import com.jean.security.model.SysResource;

import java.io.Serializable;

public class ResourceDto implements Serializable {

    public ResourceDto() {
    }

    public ResourceDto(SysResource resource) {
        this.url = resource.getUrl();
        this.name = resource.getName();
        this.description = resource.getDescription();
        this.method = resource.getMethod();
    }


    private String url;

    private String name;

    private String description;

    private String method;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
