package com.jean.auto.model.request;

import com.jean.auto.model.BaseRequest;

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
