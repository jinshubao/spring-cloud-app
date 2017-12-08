package com.jean.user.api.model.request;

import com.jean.user.api.model.BaseRequest;

import javax.validation.constraints.NotNull;

public class AddModuleRequest extends BaseRequest {

    @NotNull
    private Long projectId;

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }
}
