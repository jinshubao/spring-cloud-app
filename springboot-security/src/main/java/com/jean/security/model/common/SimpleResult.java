package com.jean.security.model.common;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * @author jinshubao
 */
public class SimpleResult implements Serializable {

    protected String resCode;

    protected String resDesc;


    public SimpleResult(String resCode, String resDesc) {
        this.resCode = resCode;
        this.resDesc = resDesc;
    }

    private SimpleResult(Builder builder) {
        this(builder.resCode, builder.resDesc);
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
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }


    public static final class Builder {
        private String resCode;
        private String resDesc;

        public Builder() {
        }

        public Builder resCode(String val) {
            resCode = val;
            return this;
        }

        public Builder resDesc(String val) {
            resDesc = val;
            return this;
        }

        public SimpleResult build() {
            return new SimpleResult(this);
        }
    }
}
