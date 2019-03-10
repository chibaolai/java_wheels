package com.bolly.jdk.proxy;

import com.bolly.jdk.proxy.res.PingResp;

public class PingGlibAction {
    public void validate(Object o) {
        System.out.println("glib validate");
    }

    public Object execute(Object o) {
        validate(o);
        System.out.println("glib execute");
        return new PingResp();
    }
}
