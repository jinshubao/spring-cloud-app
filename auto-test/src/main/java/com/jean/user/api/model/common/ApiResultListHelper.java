package com.jean.user.api.model.common;


import com.jean.user.api.constant.CommonConstant;

import java.util.List;

public class ApiResultListHelper<T> extends ApiSimpleResultHelper<List<T>> {

    protected int totalPages;

    protected long total;

    protected int number;

    protected int size;

    public ApiResultListHelper(CommonConstant.ApiResponse response, List<T> data) {
        super(response, data);
    }

    public ApiResultListHelper(String code, String desc, List<T> data) {
        super(code, desc, data);
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
