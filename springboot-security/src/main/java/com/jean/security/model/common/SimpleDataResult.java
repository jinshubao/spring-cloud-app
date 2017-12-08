package com.jean.security.model.common;

/**
 * @author jinshubao
 */
public class SimpleDataResult<T> extends SimpleResult {

    protected T data;

    private SimpleDataResult(Builder<T> builder) {
        this(builder.resCode, builder.resDesc, builder.data);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public SimpleDataResult(String resCode, String resDesc, T data) {
        super(resCode, resDesc);
        this.data = data;
    }


    public static final class Builder<T> {
        private String resCode;
        private String resDesc;
        private T data;

        public Builder() {
        }

        public Builder<T> resCode(String val) {
            resCode = val;
            return this;
        }

        public Builder<T> resDesc(String val) {
            resDesc = val;
            return this;
        }

        public Builder<T> data(T val) {
            data = val;
            return this;
        }

        public SimpleDataResult<T> build() {
            return new SimpleDataResult<>(this);
        }
    }
}
