package com.bolly.jdk.proxy.jdk;

import com.bolly.jdk.proxy.service.Action;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author bolly
 */
public class JdkProxy implements InvocationHandler {

    public Object target;

    public JdkProxy(Object action) {
        target = action;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
        Object result = null;
        System.out.println("代理执行前");
//        result = method.invoke(target,args);
        Action action = (Action) target;
        action.execute(args);
        System.out.println("代理执行后");
        return result;
    }
}
