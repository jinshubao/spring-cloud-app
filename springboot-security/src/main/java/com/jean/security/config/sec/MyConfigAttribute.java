package com.jean.security.config.sec;

import org.springframework.security.access.SecurityConfig;

/**
 * TODO
 *
 * @author jinshubao
 * @since TODO
 */
public class MyConfigAttribute extends SecurityConfig {

    private static final long serialVersionUID = -4833068400680651330L;

    private String resource;
    private String method;

    public MyConfigAttribute(String config, String resource, String method) {
        super(config);
        this.resource = resource;
        this.method = method;
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

    public boolean isAllMethd() {
        return method == null || method.trim().isEmpty() || "ALL".equals(method);
    }
}
