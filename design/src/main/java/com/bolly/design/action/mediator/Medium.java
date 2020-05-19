package com.bolly.design.action.mediator;

/**
 * @author bolly
 */
public interface Medium {
    /**
     * 客户注册
     * @param member
     */
    void register(Customer member);

    /**
     * 转发
     * @param from
     * @param ad
     */
    void relay(String from,String ad);
}
