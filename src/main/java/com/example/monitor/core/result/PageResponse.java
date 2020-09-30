package com.example.monitor.core.result;

import java.util.List;

public class PageResponse<T> {
    private long totalCount;
    private List<T> list;

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
