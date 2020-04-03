package com.bolly.collects.impl;

import com.bolly.collects.ICollect;
import com.bolly.instrument.ApmContext;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.LoaderClassPath;

import java.io.IOException;
import java.lang.instrument.Instrumentation;

public class C3p0Collect extends AbstractByteTransformCollect implements ICollect {
    public static C3p0Collect INSTANCE;
    private ApmContext context;


    public C3p0Collect(Instrumentation instrumentation, ApmContext context) throws IOException {
        super(instrumentation);
        INSTANCE = this;
        this.context = context;
    }


    @Override
    public byte[] transform(ClassLoader loader, String className) throws Exception {
        String targetClass = "com.mchange.v2.c3p0.ComboPooledDataSource";
        byte[] result = null;
        if (className != null && className.replace("/", ".").equals(targetClass)) {
            ClassPool pool = new ClassPool();
            pool.insertClassPath(new LoaderClassPath(loader));
            try {
                CtClass ctl = pool.get(targetClass);
                ctl.getConstructor("()V")
                        .insertAfter("System.getProperties().put(\"c3p0Source$agent\", $0);");
                result = ctl.toBytecode();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
