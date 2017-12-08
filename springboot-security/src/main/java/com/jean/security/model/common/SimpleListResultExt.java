package com.jean.security.model.common;

import java.util.List;

/**
 * 返回列表数据和扩展字段
 *
 * @author jinshubao
 */
public class SimpleListResultExt<T, E> extends SimpleListResult<T> {

    protected E ext;

    public SimpleListResultExt(String resCode, String resDesc, List<T> list, E ext) {
        super(resCode, resDesc, list);
        this.ext = ext;
    }

    private SimpleListResultExt(Builder<T, E> builder) {
        this(builder.resCode, builder.resDesc, builder.list, builder.ext);
    }


    public static final class Builder<T, E> {
        private String resCode;
        private String resDesc;
        private List<T> list;
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

        public Builder list(List<T> val) {
            list = val;
            return this;
        }

        public Builder ext(E val) {
            ext = val;
            return this;
        }

        public SimpleListResultExt<T, E> build() {
            return new SimpleListResultExt<>(this);
        }
    }
}
