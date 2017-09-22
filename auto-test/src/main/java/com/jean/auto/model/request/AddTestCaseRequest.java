package com.jean.auto.model.request;

import com.jean.auto.model.BaseRequest;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.Valid;
import java.util.List;

public class AddTestCaseRequest extends BaseRequest {

    @NotBlank
    private String url;

    @NotBlank
    private String method;

    @NotBlank
    private String protocol;

    @NotBlank
    private String host;

    private Integer port;

    @Valid
    private List<AddParameterRequest> parameterList;

    @Valid
    private List<AddAssertRequest> assertList;

    public List<AddParameterRequest> getParameterList() {
        return parameterList;
    }

    public void setParameterList(List<AddParameterRequest> parameterList) {
        this.parameterList = parameterList;
    }

    public List<AddAssertRequest> getAssertList() {
        return assertList;
    }

    public void setAssertList(List<AddAssertRequest> assertList) {
        this.assertList = assertList;
    }
}
