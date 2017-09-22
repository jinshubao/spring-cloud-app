package com.jean.auto.model.request;

import com.jean.auto.model.BaseRequest;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public class AddTestCaseRequest extends BaseRequest {
    @NotNull
    private Long testUnitId;

    @NotNull
    private Long apiId;

    @Valid
    private List<AddParameterRequest> parameterList;

    @Valid
    private List<AddAssertRequest> assertList;

    public Long getTestUnitId() {
        return testUnitId;
    }

    public void setTestUnitId(Long testUnitId) {
        this.testUnitId = testUnitId;
    }

    public Long getApiId() {
        return apiId;
    }

    public void setApiId(Long apiId) {
        this.apiId = apiId;
    }

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
