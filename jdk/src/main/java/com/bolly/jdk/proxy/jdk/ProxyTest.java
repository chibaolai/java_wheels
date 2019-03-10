package com.bolly.jdk.proxy.jdk;

import com.bolly.jdk.proxy.Action;
import com.bolly.jdk.proxy.PingAction;
import com.bolly.jdk.proxy.req.PingReq;
import com.bolly.jdk.proxy.res.PingResp;

public class ProxyTest {
    public static void main(String[] args) {
        ServiceProxy serviceProxy = new ServiceProxy(new PingAction());
        ServiceProxyFactory<Action> factoryBean = new ServiceProxyFactory(serviceProxy);
        Action actionProxy = factoryBean.newInstance(Action.class);
        PingReq req = new PingReq();
        PingResp resp = (PingResp) actionProxy.execute(req);
    }
}
