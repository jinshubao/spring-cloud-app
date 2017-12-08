package com.jean.user.api.model.request;

import com.jean.user.api.model.BaseRequest;
import org.hibernate.validator.constraints.NotBlank;

public class AddAssertRequest extends BaseRequest {

    @NotBlank
    private String assertKey;

    @NotBlank
    private String assertValue;

    @NotBlank
    private String assertType;


    public String getAssertKey() {
        return assertKey;
    }

    public void setAssertKey(String assertKey) {
        this.assertKey = assertKey;
    }

    public String getAssertValue() {
        return assertValue;
    }

    public void setAssertValue(String assertValue) {
        this.assertValue = assertValue;
    }

    public String getAssertType() {
        return assertType;
    }

    public void setAssertType(String assertType) {
        this.assertType = assertType;
    }
}
