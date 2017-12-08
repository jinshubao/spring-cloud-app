package com.jean.user.api.model.request;

import com.jean.user.api.model.BaseRequest;
import org.hibernate.validator.constraints.NotBlank;

public class AddParameterRequest extends BaseRequest {

    @NotBlank
    private String parameterKey;

    @NotBlank
    private String parameterValue;

    @NotBlank
    private String parameterType;

    private Boolean required;

    private String defaultValue;

    public String getParameterKey() {
        return parameterKey;
    }

    public void setParameterKey(String parameterKey) {
        this.parameterKey = parameterKey;
    }

    public String getParameterValue() {
        return parameterValue;
    }

    public void setParameterValue(String parameterValue) {
        this.parameterValue = parameterValue;
    }

    public String getParameterType() {
        return parameterType;
    }

    public void setParameterType(String parameterType) {
        this.parameterType = parameterType;
    }

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

}
