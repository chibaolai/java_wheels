package com.bolly;

import com.bolly.collects.impl.ServiceCollect;
import com.bolly.instrument.ApmContext;
import com.bolly.instrument.MockInstrumentation;
import com.bolly.service.HisServiceImpl;
import javassist.ByteArrayClassPath;
import javassist.ClassPool;

import java.util.Properties;

/**
 * @author bolly
 */
public class AppServiceMain {

    public static void main(String[] args) throws Exception {
        MockInstrumentation instrumentation = new MockInstrumentation();
        Properties pro = new Properties();
        pro.put("service.include","com.bolly.service.*");
        pro.put("service.exclude","com.bolly.service.HisServiceImpl");
        ApmContext apmContext = new ApmContext(instrumentation,pro);
        ServiceCollect serviceCollect = new ServiceCollect(instrumentation,apmContext);

        String name = "com.bolly.service.HisServiceImpl";
        byte[] classBytes = serviceCollect.transform(AppServiceMain.class.getClassLoader(),name);

        ClassPool pool = new ClassPool();
        pool.insertClassPath(new ByteArrayClassPath(name, classBytes));
        pool.get(name).toClass();

        new HisServiceImpl().getUser("111","dddd");
    }
}
