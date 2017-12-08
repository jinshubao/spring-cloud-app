package com.jean.security.model.common;

/**
 * @author jinshubao
 * @date 2016/9/29
 */
public class SimpleDataResultExt<T, E> extends SimpleDataResult<T> {

    protected E ext;

    public SimpleDataResultExt(String resCode, String resDesc, T data, E ext) {
        super(resCode, resDesc, data);
        this.ext = ext;
    }

    private SimpleDataResultExt(Builder<T, E> builder) {
        this(builder.resCode, builder.resDesc, builder.data, builder.ext);
    }

    public E getExt() {
        return ext;
    }

    public void setExt(E ext) {
        this.ext = ext;
    }


    public static final class Builder<T, E> {
        private String resCode;
        private String resDesc;
        private T data;
        private E ext;

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

        public Builder data(T val) {
            data = val;
            return this;
        }

        public Builder ext(E val) {
            ext = val;
            return this;
        }

        public SimpleDataResultExt<T, E> build() {
            return new SimpleDataResultExt<>(this);
        }
    }
}
