package com.bolly.jdk.jvm.OOM;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * 直接内存溢出测试
 * -Xmx20M -XX:MaxDirectMemorySize=10M
 */
public class DirectoryMemoryOOM implements OutOfMemory{
    private static final int ONE_MB = 1024 * 1024;
    private static int count = 1;

    public void runOOM() {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            Unsafe unsafe = (Unsafe) field.get(null);
            while (true) {
                unsafe.allocateMemory(ONE_MB);
                count++;
            }

        } catch (Exception e) {
            System.out.println("Exception:instance created " + count);
            e.printStackTrace();
        } catch (Error e) {
            System.out.println("Error:instance created " + count);
            e.printStackTrace();
        }
    }
}
