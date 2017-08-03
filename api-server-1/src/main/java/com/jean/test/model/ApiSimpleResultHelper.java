package com.jean.test.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("接口返回数据")
public class ApiSimpleResultHelper<T> extends ApiResultHelper {
    public ApiSimpleResultHelper() {
    }

    public ApiSimpleResultHelper(String resCode) {
        super(resCode);
    }

    public ApiSimpleResultHelper(String resCode, String resDesc) {
        super(resCode, resDesc);
    }

    public ApiSimpleResultHelper(String resCode, String resDesc, T data) {
        super(resCode, resDesc);
        this.data = data;
    }

    @ApiModelProperty(value = "返回数据")
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
