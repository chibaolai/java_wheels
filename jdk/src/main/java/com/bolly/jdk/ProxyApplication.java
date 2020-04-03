package com.bolly.jdk;

import com.bolly.jdk.proxy.service.Action;
import com.bolly.jdk.proxy.service.impl.PingAction;
import com.bolly.jdk.proxy.service.impl.PingGlibAction;
import com.bolly.jdk.proxy.cglib.GlibProxy;
import com.bolly.jdk.proxy.jdk.JdkProxy;
import com.bolly.jdk.proxy.jdk.JdkProxyFactory;
import com.bolly.jdk.proxy.service.req.PingReq;

/**
 * @author bolly
 */
public class ProxyApplication {

    public static void main(String[] args) {
        runGlibProxy();
        runJdkProxy();
    }

    private static void runGlibProxy() {
        GlibProxy proxy = new GlibProxy();
        PingGlibAction action = (PingGlibAction) proxy.newInstance(new PingGlibAction());
        PingReq req = new PingReq();
        action.execute(req);
    }

    private static void runJdkProxy() {
        JdkProxy jdkProxy = new JdkProxy(new PingAction());
        JdkProxyFactory<Action> factoryBean = new JdkProxyFactory(jdkProxy);
        Action actionProxy = factoryBean.newInstance(Action.class);
        PingReq req = new PingReq();
        actionProxy.execute(req);
    }
}
