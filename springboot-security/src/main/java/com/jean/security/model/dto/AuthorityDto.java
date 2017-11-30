package com.jean.security.model.dto;

import java.io.Serializable;

/**
 * TODO
 *
 * @author jinshubao
 * @since TODO
 */
public class AuthorityDto implements Serializable {

    private static final long serialVersionUID = 6563479199817858362L;

    private String authority;

    private String resource;

    private String method;

    public AuthorityDto() {
    }

    public AuthorityDto(String authority, String resource, String method) {
        this.authority = authority;
        this.resource = resource;
        this.method = method;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
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
}
