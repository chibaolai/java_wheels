package com.bolly.jdk.jvm;

public class Jconsole {

    /**
     * javap -v Jconsole > test.txt
     * jconsole
     * @param args
     */
    public static void main(String[] args) {
        while (true) {
            try {
                Thread.sleep(40000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(methodOne(1));
        }

    }

    public static int methodOne(int i) {
        int j = 1;
        int sum = i + j;
        generate();
        return sum;
    }

    public static int[] generate() {
        return new int[1024 * 256 * 2];
    }
}
