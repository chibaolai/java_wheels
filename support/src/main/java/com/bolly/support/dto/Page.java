package com.bolly.support.dto;

import java.util.List;

public interface Page<T> {

    /**
     * 当前的页码
     * @return
     */
    int getPageNo();

    /**
     * 每页的数量
     * @return
     */
    int getPageSize();

    /**
     * 当前页的内容
     * @return
     */
    List<T> getContent();

    /**
     * 总页数
     * @return
     */
    int getPages();

    /**
     * 总记录数
     * @return
     */
    long getTotal();

}
