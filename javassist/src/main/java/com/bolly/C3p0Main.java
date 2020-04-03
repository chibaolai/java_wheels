package com.bolly;

import com.bolly.collects.impl.C3p0Collect;
import com.bolly.instrument.ApmContext;
import com.bolly.instrument.MockInstrumentation;
import com.bolly.service.C3p0ServiceImpl;
import javassist.ByteArrayClassPath;
import javassist.ClassPool;

import java.util.Properties;

/**
 * @author bolly
 */
public class C3p0Main {

    public static void main(String[] args) throws Exception {
        MockInstrumentation instrumentation = new MockInstrumentation();
        Properties pro = new Properties();
        pro.put("service.include","com.bolly.service.*");
        pro.put("service.exclude","");
        ApmContext apmContext = new ApmContext(instrumentation,pro);
        C3p0Collect serviceCollect = new C3p0Collect(instrumentation,apmContext);

        String name = "com.mchange.v2.c3p0.ComboPooledDataSource";
        byte[] classBytes = serviceCollect.transform(C3p0Main.class.getClassLoader(),name);

        ClassPool pool = new ClassPool();
        pool.insertClassPath(new ByteArrayClassPath(name, classBytes));
        pool.get(name).toClass();



        C3p0ServiceImpl c3p0Service = new C3p0ServiceImpl();
        byte[] bytes = new byte[1024];
        int size = System.in.read(bytes);
        String sql = new String(bytes, 0, size);
        c3p0Service.exec(sql);
    }


}
