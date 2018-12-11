package com.bolly.jdk.jvm;

import java.lang.reflect.Method;
//import net.sf.cglib.proxy.Enhancer;

/**
 * 方法区溢出
 * -XX:PermSize=10M -XX:MaxPermSize=10M
 */
public class MethodAreaOutOfMemory {

    public static void main(String[] args) {
        while(true){
//            Enhancer enhancer = new Enhancer();
//            enhancer.setSuperclass(TestCase.class);
//            enhancer.setUseCache(false);
//            enhancer.setCallback(new MethodInterceptor() {
//                @Override
//                public Object intercept(Object arg0, Method arg1, Object[] arg2, MethodProxy arg3) throws Throwable {
//                    return arg3.invokeSuper(arg0, arg2);
//                }
//            });
//            enhancer.create();

        }

    }
}
