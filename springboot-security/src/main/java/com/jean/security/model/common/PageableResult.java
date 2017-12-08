package com.jean.security.model.common;

import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author jinshubao
 */
public class PageableResult<T> extends SimpleListResult {

    protected int totalCount;

    protected int page;

    protected int size;

    protected int totalPages;

    public PageableResult(String resCode, String resDesc, List list, int totalCount, int page, int size, int totalPages) {
        super(resCode, resDesc, list);
        this.totalCount = totalCount;
        this.page = page;
        this.size = size;
        this.totalPages = totalPages;
    }

    private PageableResult(Builder<T> builder) {
        this(builder.resCode, builder.resDesc, builder.list, builder.totalCount, builder.page, builder.size, builder.totalPages);
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }


    public static final class Builder<T> {
        private String resCode;
        private String resDesc;
        private List<T> list;
        private int totalCount;
        private int page;
        private int size;
        private int totalPages;

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

        public Builder<T> totalCount(int val) {
            totalCount = val;
            return this;
        }

        public Builder<T> page(int val) {
            page = val;
            return this;
        }

        public Builder<T> size(int val) {
            size = val;
            return this;
        }

        public Builder<T> totalPages(int val) {
            totalPages = val;
            return this;
        }

        public Builder<T> withPage(Page<T> page) {
            this.list = page.getContent();
            this.totalCount = (int) page.getTotalElements();
            this.page = page.getNumber() + 1;
            this.size = page.getSize();
            this.totalPages = page.getTotalPages();
            return this;
        }

        public PageableResult<T> build() {
            return new PageableResult<>(this);
        }
    }
}
