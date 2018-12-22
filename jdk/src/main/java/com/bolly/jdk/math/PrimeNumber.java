package com.bolly.jdk.math;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * 判断100-200之间有多少个素数，并打印出所有的素数清单
 */
public class PrimeNumber {

    private Set suNumber = new TreeSet();

    public static void main(String[] args) {
        PrimeNumber primeNumber = new PrimeNumber();
        primeNumber.printSuNum(100,200);
    }

    /**
     * 打印素数
     * @param begin
     * @param end
     */
    private void printSuNum(int begin,int end) {
        for (int num = begin; num <= end; num++) {
            for (int i = num; i > 1; i--) {
                isSuNum(i, i - 1);
            }
        }
        Iterator iterator = suNumber.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }


    /**
     * 递归判断素数
     * @param num
     * @param div
     * @return
     */
    private boolean isSuNum(int num, int div) {
        if(num % 2 == 0) {
            return false;
        }
        if (div < 2) {
            suNumber.add(num);
            return true;
        }
        if (num % div == 0) {
            return false;
        }
        return isSuNum(num, div - 1);
    }
}
