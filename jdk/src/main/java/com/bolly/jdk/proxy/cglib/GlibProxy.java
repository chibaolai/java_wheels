package com.bolly.jdk.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author bolly
 */
public class GlibProxy implements MethodInterceptor {
    private Object target;

    public Object newInstance(Object target) {
        this.target = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }


    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        Object result;
        System.out.println("cglib 代理执行前");
        result = method.invoke(target, args);
        System.out.println("cglib 代理执行后");
        return result;
    }
}
