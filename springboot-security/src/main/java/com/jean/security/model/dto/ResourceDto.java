package com.jean.security.model.dto;

import java.io.Serializable;

/**
 * TODO
 *
 * @author jinshubao
 * @since TODO
 */
public class ResourceDto implements Serializable {

    private static final long serialVersionUID = 1908385730155551800L;

    private String resource;

    private String method;

    private String authority;

    public ResourceDto() {
    }

    public ResourceDto(String resource, String method, String authority) {
        this.resource = resource;
        this.method = method;
        this.authority = authority;
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

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
