package com.jean.security.model.common;

import java.util.List;

/**
 * 只返回列表数据
 *
 * @author jinshubao
 */
public class SimpleListResult<T> extends SimpleResult {

    protected List<T> list;

    public SimpleListResult(String resCode, String resDesc, List<T> list) {
        super(resCode, resDesc);
        this.list = list;
    }

    private SimpleListResult(Builder<T> builder) {
        this(builder.resCode, builder.resDesc, builder.list);
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }


    public static final class Builder<T> {
        private String resCode;
        private String resDesc;
        private List<T> list;

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

        public Builder<T> list(List<T> val) {
            list = val;
            return this;
        }

        public SimpleListResult<T> build() {
            return new SimpleListResult<>(this);
        }
    }
}
