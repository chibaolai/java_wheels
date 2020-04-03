package com.bolly.jdk.jvm.OOM;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
//import net.sf.cglib.proxy.Enhancer;

/**
 * 方法区溢出
 * -XX:PermSize=10M -XX:MaxPermSize=10M
 */
public class MethodAreaOOM implements OutOfMemory{

    public void runOOM() {
        while(true){
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(MethodAreaOOM.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object arg0, Method arg1, Object[] arg2, MethodProxy arg3) throws Throwable {
                    return arg3.invokeSuper(arg0, arg2);
                }
            });
            enhancer.create();

        }
    }
}
