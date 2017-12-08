package com.jean.user.api.model.common;


import com.jean.user.api.constant.CommonConstant;

import java.io.Serializable;

public class ApiResultHelper implements Serializable {

    protected String code;

    protected String desc;


    public ApiResultHelper(CommonConstant.ApiResponse response) {
        this.code = response.getCode();
        this.desc = response.getDesc();
    }

    public ApiResultHelper(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
