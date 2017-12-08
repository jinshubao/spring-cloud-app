package com.jean.security.constant;

import java.util.Arrays;
import java.util.Optional;

/**
 * TODO
 *
 * @author jinshubao
 * @since TODO
 */
public class CommConstant {

    public enum Response {
        /**
         * 成功响应
         */
        SUCCESS("0000", "ok"),
        ERROR("9999", "error");

        String resCode;
        String resDesc;

        Response(String resCode, String resDesc) {
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

        public static String getResDesc(String resCode) {
            Optional<Response> response = Arrays.stream(values()).filter(item -> item.resCode.equals(resCode)).findFirst();
            return response.isPresent() ? response.get().resDesc : null;
        }
    }
}
