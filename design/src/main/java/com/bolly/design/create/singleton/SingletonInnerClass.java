package com.bolly.design.create.singleton;

/**
 * @author bolly
 * 懒汉模式，只有调用getInstance 才会加载SingletonHolder 内部类，从而实例初始化
 */
public class SingletonInnerClass {


    public static SingletonInnerClass getInstance() {
        return SingletonHolder.singletonHolder;
    }


    private final static class SingletonHolder {
        private static SingletonInnerClass singletonHolder = new SingletonInnerClass();
    }
}
