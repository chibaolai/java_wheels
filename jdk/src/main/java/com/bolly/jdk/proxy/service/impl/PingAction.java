package com.bolly.jdk.proxy.service.impl;

import com.bolly.jdk.proxy.service.Action;
import com.bolly.jdk.proxy.service.res.PingResp;

public class PingAction implements Action {
    @Override
    public void validate(Object o) {
        System.out.println("validate");
    }

    @Override
    public Object execute(Object o) {
        validate(o);
        System.out.println("execute");
        return new PingResp();
    }
}
