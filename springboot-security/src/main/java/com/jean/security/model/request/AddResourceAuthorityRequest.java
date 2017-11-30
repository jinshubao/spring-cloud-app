package com.jean.security.model.request;

/**
 * TODO
 *
 * @author jinshubao
 * @since TODO
 */
public class AddResourceAuthorityRequest {

    private Long resourceId;

    private Long authoritiyId;

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public Long getAuthoritiyId() {
        return authoritiyId;
    }

    public void setAuthoritiyId(Long authoritiyId) {
        this.authoritiyId = authoritiyId;
    }
}
