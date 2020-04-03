package com.bolly;

import com.bolly.collects.impl.JdbcCommonCollect;
import com.bolly.instrument.ApmContext;
import com.bolly.instrument.MockInstrumentation;
import com.bolly.service.JdbcServiceImpl;
import javassist.ByteArrayClassPath;
import javassist.ClassPool;

import java.util.Properties;

/**
 * @author bolly
 */
public class AppJdbcMain {


    public static void main(String[] args) throws Exception {
        MockInstrumentation instrumentation = new MockInstrumentation();
        Properties pro = new Properties();
        pro.put("service.include","com.bolly.service.*");
        pro.put("service.exclude","");
        ApmContext apmContext = new ApmContext(instrumentation,pro);
        JdbcCommonCollect serviceCollect = new JdbcCommonCollect(instrumentation,apmContext);

        String name = "com.mysql.jdbc.NonRegisteringDriver";
        byte[] classBytes = serviceCollect.transform(AppJdbcMain.class.getClassLoader(),name);

        ClassPool pool = new ClassPool();
        pool.insertClassPath(new ByteArrayClassPath(name, classBytes));
        pool.get(name).toClass();

        new JdbcServiceImpl().runJdbc();
    }
}
