package com.bolly.jdk.jvm.OOM;

/**
 * 栈内存溢出
 * -Xss128k
 */
public class StackOverFlow implements OutOfMemory{
    private int i ;
    public void plus() {
        i++;
        plus();
    }

    public void runOOM() {
        StackOverFlow stackOverFlow = new StackOverFlow();
        try {
            stackOverFlow.plus();
        } catch (Exception e) {
            System.out.println("Exception:stack length:"+stackOverFlow.i);
            e.printStackTrace();
        } catch (Error e) {
            System.out.println("Error:stack length:"+stackOverFlow.i);
            e.printStackTrace();
        }
    }
}
