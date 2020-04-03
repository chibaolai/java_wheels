package com.bolly.javassist;


import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Objects;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtField;
import javassist.CtField.Initializer;
import javassist.CtMethod;
import javassist.CtNewMethod;

public class JavassistLearn {


    public static void main(String[] args) throws Exception {
        ClassPool cp = ClassPool.getDefault();
        CtClass ctClass = cp.makeClass("com.bolly.javassist.JavassistClass");

        CtField ctField = new CtField(cp.get("java.lang.String"), "name", ctClass);
        ctField.setModifiers(Modifier.PRIVATE);
        ctClass.addMethod(CtNewMethod.setter("setName", ctField));
        ctClass.addMethod(CtNewMethod.getter("getName", ctField));
        ctClass.addField(ctField, Initializer.constant("default"));


        StringBuffer body = null;
        CtConstructor ctConstructor = new CtConstructor(new CtClass[]{}, ctClass);
        body = new StringBuffer();
        body.append("{\n name=\"me\";\n}");
        ctConstructor.setBody(body.toString());
        ctClass.addConstructor(ctConstructor);

        //参数：  1：返回类型  2：方法名称  3：传入参数类型  4：所属类CtClass
        CtMethod ctMethod = new CtMethod(CtClass.voidType, "execute", new CtClass[]{}, ctClass);
        ctMethod.setModifiers(Modifier.PUBLIC);
        body = new StringBuffer();
        body.append("{\n System.out.println(name);");
        body.append("\n System.out.println(\"execute ok\");");
        body.append("\n return ;");
        body.append("\n}");
        ctMethod.setBody(body.toString());
        ctClass.addMethod(ctMethod);
//        ctClass.writeFile("/Users/bolly/IdeaProjects/workspace_todo/java_wheels/javassist/out/production/classes");

//        Class<?> c = ctClass.toClass();
//        Object o = c.newInstance();
//        Method method = o.getClass().getMethod("execute", new Class[]{});
//        //调用字节码生成类的execute方法
//        method.invoke(o, new Object[]{});
        ClassPool pool = ClassPool.getDefault();
//        pool.appendClassPath("/Users/bolly/IdeaProjects/workspace_todo/java_wheels/javassist/");
        CtClass ctClass1 = pool.get("com.bolly.javassist.JavassistClass");
        Object ins = ctClass1.toClass().newInstance();
        ctClass1.defrost();
        CtMethod exeCtMethod = ctClass1.getDeclaredMethod("execute");
        exeCtMethod.insertBefore("System.out.println(\"方法请求前\");");
        exeCtMethod.insertAfter("System.out.println(\"方法请求后\");");
        ctClass1.freeze();

        ins.getClass().getMethod("execute").invoke(ins);

        ctClass.detach();
    }

}
