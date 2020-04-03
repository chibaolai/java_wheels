package com.bolly.jdk;

import com.bolly.jdk.jvm.OOM.*;

/**
 * @author bolly
 */
public class OOMApplication {
    public static void main(String[] args) {
        new ConstantOOM().runOOM();
        new DirectoryMemoryOOM().runOOM();
        new MethodAreaOOM().runOOM();
        new StackOverFlow().runOOM();
    }
}
