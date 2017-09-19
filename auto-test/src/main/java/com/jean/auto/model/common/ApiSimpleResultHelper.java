package com.jean.auto.model.common;

import com.jean.auto.constant.CommonConstant;

public class ApiSimpleResultHelper<T> extends ApiResultHelper {

    protected T data;

    public ApiSimpleResultHelper(CommonConstant.ApiResponse response, T data) {
        super(response);
        this.data = data;
    }

    public ApiSimpleResultHelper(String code, String desc, T data) {
        super(code, desc);
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
