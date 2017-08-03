package com.jean.test.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel("接口返回")
public class ApiResultHelper implements Serializable {

    @ApiModelProperty(value = "返回代码", example = "0000", required = true)
    private String resCode;

    @ApiModelProperty(value = "返回代码描述")
    private String resDesc;

    public ApiResultHelper() {
    }

    public ApiResultHelper(String resCode) {
        this.resCode = resCode;
    }

    public ApiResultHelper(String resCode, String resDesc) {
        this.resCode = resCode;
        this.resDesc = resDesc;
    }

    public String getResCode() {
        return resCode;
    }

    public void setResCode(String resCode) {
        this.resCode = resCode;
    }

    public String getResDesc() {
        return resDesc;
    }

    public void setResDesc(String resDesc) {
        this.resDesc = resDesc;
    }


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ApiResultHelper{");
        sb.append("resCode='").append(resCode).append('\'');
        sb.append(", resDesc='").append(resDesc).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
