package com.bolly.instrument;

import com.bolly.collects.ICollect;
import com.bolly.collects.impl.JdbcCommonCollect;
import com.bolly.collects.impl.ServiceCollect;
import com.bolly.filter.IFilter;
import com.bolly.filter.JSONFormat;
import com.bolly.output.IOutput;
import com.bolly.output.JulOutput;

import java.lang.instrument.Instrumentation;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author bolly
 */
public class ApmContext {
    private Instrumentation instrumentation;
    private Properties properties;

    List<ICollect> collects = new ArrayList();
    IFilter filter;
    IOutput output;

    public ApmContext(Instrumentation instrumentation, Properties properties) {
        this.instrumentation = instrumentation;
        this.properties = properties;

        collects.add(new ServiceCollect(instrumentation,this));
        collects.add(new JdbcCommonCollect(instrumentation,this));

        //filter 注册
        filter = new JSONFormat();
        //输出器注册
        output = new JulOutput();
    }

    // 递交采集结果
    public void submitCollectResult(Object value) {
        // TODO 基于线程后台执行
        value = filter.doFilter(value);
        output.out(value);
    }

    public String getConfig(String key) {
        return properties.getProperty(key);
    }

    public List<ICollect> getCollects() {
        return collects;
    }
}
