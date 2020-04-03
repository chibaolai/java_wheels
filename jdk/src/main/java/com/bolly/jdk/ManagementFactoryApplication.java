package com.bolly.jdk;

import com.bolly.jdk.jvm.GCInfo;
import com.bolly.jdk.jvm.MemoryInfo;
import com.bolly.jdk.jvm.ThreadInfo;
import com.bolly.support.utils.JacksonUtils;

/**
 * @author bolly
 */
public class ManagementFactoryApplication {


    public static void main(String[] args) {
        System.out.println(JacksonUtils.marshal(ThreadInfo.collectThreadInfo()));

        MemoryInfo memoryInfo = new MemoryInfo();
        System.out.println(JacksonUtils.marshal(memoryInfo));


        GCInfo GCInfo = new GCInfo();
        System.out.println(JacksonUtils.marshal(GCInfo.collectGCInfo()));
    }
}
