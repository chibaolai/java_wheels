package com.bolly.jdk.proxy.jdk;

import java.lang.reflect.Proxy;

/**
 * @author bolly
 */
public class JdkProxyFactory<T> {
    private JdkProxy proxy;

    public JdkProxyFactory(JdkProxy jdkProxy) {
        proxy = jdkProxy;
    }

    public T newInstance(Class<T> interfaceClass) {

        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, proxy);
    }
}
