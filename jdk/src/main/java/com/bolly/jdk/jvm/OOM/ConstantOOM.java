package com.bolly.jdk.jvm.OOM;

import java.util.ArrayList;
import java.util.List;

/**
 * -XX:PermSize=10M -XX:MaxPermSize=10M
 * 常量池内存溢出
 */
public class ConstantOOM implements OutOfMemory{

    public void runOOM() {
        try {
            List<String> strings = new ArrayList<String>();
            int i = 0;
            while (true) {
                strings.add(String.valueOf(i++).intern());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
