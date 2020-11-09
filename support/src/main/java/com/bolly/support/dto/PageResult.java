package com.bolly.support.dto;

import java.util.ArrayList;
import java.util.List;

class PageResult<T> implements Page<T> {

    private long total;

    private Pageable pageable;

    private List<T> content = new ArrayList<>();

    public PageResult(Pageable pageable, long total) {
        this.pageable = pageable;
        this.total = total;
    }

    public PageResult(Pageable pageable, List<T> content, long total) {
        this.pageable = pageable;
        this.total = total;
        if (content != null) {
            this.content = content;
        }
    }

    @Override
    public int getPageNo() {
        // 实际分页的总页数
        int pages = getPages();
        // 期望的当前页码
        int pageNo = pageable.getPageNo();
        // 期望的当前页码超过实际分页的总页数的情况
        if (pageNo > pages) {
            return pages;
        }
        return pageNo;
    }

    @Override
    public int getPageSize() {
        return pageable.getPageSize();
    }

    @Override
    public List<T> getContent() {
        return content;
    }

    @Override
    public int getPages() {
        return (int) ((total + pageable.getPageSize() - 1) / pageable.getPageSize());
    }

    @Override
    public long getTotal() {
        return total;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

}
