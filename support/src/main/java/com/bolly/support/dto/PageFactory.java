package com.bolly.support.dto;

import org.apache.commons.collections.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * PageFactory
 *
 * @author Erich
 *
 */
public class PageFactory {

    private PageFactory() {

    }

    /**
     * 创建Page对象
     *
     * @param content
     *            记录集合
     * @param pageable
     *            分页相关参数
     * @param total
     *            总记录数
     * @return
     */
    public static <T> Page<T> createPage(List<T> content, Pageable pageable, long total) {
        return new PageResult<T>(pageable, content, total);
    }

    /**
     * 将给定集合按给定pageable对象在内存中完成分页
     * @param totalElements
     * @param pageable
     * @return
     */
    public static <T> Page<T> createPage(List<T> totalElements, Pageable pageable) {
        int total = totalElements.size();
        PageResult<T> page = new PageResult<>(pageable, total);
        int pageNo = page.getPageNo();
        int pageSize = page.getPageSize();
        int pages = page.getPages();
        int fromIndex = (pageNo - 1) * pageSize;
        int toIndex;
        if (pageNo == pages) {
            toIndex = total;
        } else {
            toIndex = pageNo * pageSize;
        }
        List<T> content;
        if(CollectionUtils.isEmpty(totalElements)) {
        	content = Collections.emptyList();
        }else {
        	content = totalElements.subList(fromIndex, toIndex);
        }
        page.setContent(content);
        return page;
    }

}
