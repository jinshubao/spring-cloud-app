package com.jean.security.model.common;

import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author jinshubao
 */
public class PageableResultExt<T, E> extends PageableResult<T> {

    protected E ext;

    public PageableResultExt(String resCode, String resDesc, List list, int totalCount, int page, int size, int totalPages, E ext) {
        super(resCode, resDesc, list, totalCount, page, size, totalPages);
        this.ext = ext;
    }

    private PageableResultExt(Builder<T, E> builder) {
        this(builder.resCode, builder.resDesc, builder.list, builder.totalCount, builder.page, builder.size, builder.totalPages, builder.ext);
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
        private List<T> list;
        private int totalCount;
        private int page;
        private int size;
        private int totalPages;
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

        public Builder totalCount(int val) {
            totalCount = val;
            return this;
        }

        public Builder page(int val) {
            page = val;
            return this;
        }

        public Builder size(int val) {
            size = val;
            return this;
        }

        public Builder totalPages(int val) {
            totalPages = val;
            return this;
        }

        public Builder ext(E val) {
            ext = val;
            return this;
        }

        public Builder withPage(Page<T> page) {
            this.list = page.getContent();
            this.totalCount = (int) page.getTotalElements();
            this.page = page.getNumber() + 1;
            this.totalPages = page.getTotalPages();
            this.size = page.getSize();
            return this;
        }

        public PageableResultExt<T, E> build() {
            return new PageableResultExt<>(this);
        }
    }
}
