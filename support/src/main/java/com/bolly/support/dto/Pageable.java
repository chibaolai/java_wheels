package com.bolly.support.dto;

import java.io.Serializable;

/**
 * 分页参数
 * @author Erich
 *
 */
public interface Pageable extends Serializable {

    /**
     * 页码(从1开始)
     * @return
     */
    int getPageNo();

    /**
     * 期望每页的记录数
     * @return
     */
    int getPageSize();

    int getOffset();

}
