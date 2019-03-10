package com.bolly.jdk.proxy.jdk;

import java.lang.reflect.Proxy;

public class ServiceProxyFactory<T> {
    private ServiceProxy proxy;

    public ServiceProxyFactory(ServiceProxy serviceProxy) {
        proxy = serviceProxy;
    }

    protected T newInstance(Class<T> interfaceClass) {

        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, proxy);
    }
}
