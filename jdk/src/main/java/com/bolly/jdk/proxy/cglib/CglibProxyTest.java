package com.bolly.jdk.proxy.cglib;

import com.bolly.jdk.proxy.PingGlibAction;
import com.bolly.jdk.proxy.req.PingReq;

public class CglibProxyTest {

    public static void main(String[] args) {
        ServiceGlibProxy proxy = new ServiceGlibProxy();
        PingGlibAction action = (PingGlibAction) proxy.newInstance(new PingGlibAction());
        PingReq req = new PingReq();
        action.execute(req);
    }
}
