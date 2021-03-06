package com.bolly.collects.impl;

import com.bolly.collects.common.AgentByteBuild;
import com.bolly.collects.ICollect;
import com.bolly.collects.common.WildcardMatcher;
import com.bolly.instrument.ApmContext;
import com.bolly.model.ServiceStatistics;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.Modifier;

import java.lang.instrument.Instrumentation;

public class ServiceCollect extends AbstractByteTransformCollect implements ICollect {
    public static ServiceCollect INSTANCE;
    private WildcardMatcher excludeMatcher = null; // 排除非哪些类
    private WildcardMatcher includeMatcher = null;// 包含哪些类
    private ApmContext context;
    private static final String beginSrc;
    private static final String endSrc;
    private static final String errorSrc;

    static {
        StringBuilder sbuilder = new StringBuilder();
        sbuilder.append("com.bolly.collects.impl.ServiceCollect instance= ");
        sbuilder.append("com.bolly.collects.impl.ServiceCollect.INSTANCE;\r\n");
        sbuilder.append("com.bolly.model.ServiceStatistics statistic =instance.begin(\"%s\",\"%s\");");
        beginSrc = sbuilder.toString();
        sbuilder = new StringBuilder();
        sbuilder.append("instance.end(statistic);");
        endSrc = sbuilder.toString();
        sbuilder = new StringBuilder();
        sbuilder.append("instance.error(statistic,e);");
        errorSrc = sbuilder.toString();
    }


    public ServiceCollect(Instrumentation instrumentation, ApmContext context) {
        super(instrumentation);
        INSTANCE = this;
        this.context = context;

        if (context.getConfig("service.include") != null) {
            includeMatcher = new WildcardMatcher(context.getConfig("service.include"));
        } else {
            System.err.println("[error]未配置 'service.include'参数无法监控service服务方法");
        }
        if (context.getConfig("service.exclude") != null) {
            excludeMatcher = new WildcardMatcher(context.getConfig("service.exclude"));
        }
    }


    public ServiceStatistics begin(String className, String methodName) {
        ServiceStatistics bean = new ServiceStatistics();
        bean.setRecordTime(System.currentTimeMillis());
//        bean.setHostIp();
//        bean.setHostName();
        bean.setBegin(System.currentTimeMillis());
        bean.setServiceName(className);
        bean.setMethodName(methodName);
        bean.setSimpleName(className.substring(className.lastIndexOf(".")));
        bean.setModelType("service");
        return bean;
    }

    public void error(ServiceStatistics bean, Throwable e) {
        bean.setErrorType(e.getClass().getSimpleName());
        bean.setErrorMsg(e.getMessage());
    }

    public void end(ServiceStatistics bean) {
        bean.setEnd(System.currentTimeMillis());
        bean.setUseTime(bean.end - bean.begin);
        context.submitCollectResult(bean);
    }


    @Override
    public byte[] transform(ClassLoader loader, String className) throws Exception {
        if (includeMatcher == null) {
            return null;
        } else if (!includeMatcher.matches(className)) { // 包含指定类
            return null;
        } else if (excludeMatcher != null && excludeMatcher.matches(className)) { // 排除指定类
            return null;
        }

        CtClass ctclass = toCtClass(loader, className);
        AgentByteBuild byteLoade = new AgentByteBuild(className, loader, ctclass);
        CtMethod[] methods = ctclass.getDeclaredMethods();
        for (CtMethod m : methods) {
            // 屏蔽非公共方法
            if (!Modifier.isPublic(m.getModifiers())) {
                continue;
            }
            // 屏蔽静态方法
            if (Modifier.isStatic(m.getModifiers())) {
                continue;
            }
            // 屏蔽本地方法
            if (Modifier.isNative(m.getModifiers())) {
                continue;
            }
            AgentByteBuild.MethodSrcBuild build = new AgentByteBuild.MethodSrcBuild();
            build.setBeginSrc(String.format(beginSrc, className, m.getName()));
            build.setEndSrc(endSrc);
            build.setErrorSrc(errorSrc);
            byteLoade.updateMethod(m, build);
        }
        return byteLoade.toBytecote();
    }
}
