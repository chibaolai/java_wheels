package com.bolly.jdk.proxy;

import com.bolly.jdk.proxy.res.PingResp;

public class PingAction implements Action{
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
