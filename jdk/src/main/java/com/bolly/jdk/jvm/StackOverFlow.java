package com.bolly.jdk.jvm;

/**
 * 栈内存溢出
 * -Xss128k
 */
public class StackOverFlow {
    private int i ;
    public void plus() {
        i++;
        plus();
    }

    public static void main(String[] args) {
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
