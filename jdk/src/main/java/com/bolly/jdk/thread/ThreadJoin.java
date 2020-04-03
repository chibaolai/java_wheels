package com.bolly.jdk.thread;

public class ThreadJoin {
    static int a = 1;

    public static void main(String[] args) {
        Thread tb = new Thread(()->{
            a = 2;
        });

        Thread ta = new Thread(()->{
            try {
                tb.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(a);
        });

        ta.start();
        tb.start();
    }
}
