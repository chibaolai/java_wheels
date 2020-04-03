package com.bolly.collects.impl;

import javassist.*;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author bolly
 */
public abstract class AbstractByteTransformCollect {
    private static Map<ClassLoader, ClassPool> classPoolMap = new ConcurrentHashMap<ClassLoader, ClassPool>();

    public AbstractByteTransformCollect(Instrumentation instrumentation) {
        instrumentation.addTransformer(new ClassFileTransformer() {

            @Override
            public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
                className = className.trim().replace("/", ".");
                try {
                    return AbstractByteTransformCollect.this.transform(loader, className);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        });
    }


    // 插桩
    public abstract byte[] transform(ClassLoader loader, String className) throws Exception;


    protected static CtClass toCtClass(ClassLoader loader, String className) throws NotFoundException {
        if (!classPoolMap.containsKey(loader)) {
            ClassPool classPool = new ClassPool();
            classPool.insertClassPath(new LoaderClassPath(loader));
            classPoolMap.put(loader, classPool);
        }
        ClassPool cp = classPoolMap.get(loader);
        className = className.replaceAll("/", ".");
        return cp.get(className);
    }
}
