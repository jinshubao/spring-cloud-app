package com.jean.user.api.constant;

public class CommonConstant {
    public static final String DEFAULT_PAGE_SIZE = "20";

    //页码不能小于最小值
    public static final String RE_ERROR__PAGE_NUMBER_LESS_THAN_MINIMUM_VALUE = "页码不能小于{value}";
    //每页大小不能大于最大值
    public static final String RE_ERROR__PAGE_SIZE_GREATER_THAN_MAXIMUM_VALUE = "每页大小不能大于{value}";
    //每页大小不能小于最小值
    public static final String RE_ERROR__PAGE_SIZE_LESS_THAN_MINIMUM_VALUE = "每页大小不能小于{value}";


    public enum ApiResponse {

        SUCCESS("0000", "ok"),
        PARAMETER_ERROR("9000", "参数错误"),
        SYSTEM_ERROR("9999", "系统忙，请稍后重试");

        String code;
        String desc;

        ApiResponse(String code, String desc) {
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

    public enum ParameterType {
        HEADER("header"),
        BODY("body");
        String value;

        ParameterType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
