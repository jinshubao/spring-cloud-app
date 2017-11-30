package com.jean.security.model.request;

import java.util.Set;

/**
 * TODO
 *
 * @author jinshubao
 * @since TODO
 */
public class UpdateResourceAuthorityRequest {

    private Long resourceId;

    private Set<Long> authorities;

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public Set<Long> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Long> authorities) {
        this.authorities = authorities;
    }
}
