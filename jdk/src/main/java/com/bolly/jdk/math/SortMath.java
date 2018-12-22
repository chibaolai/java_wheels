package com.bolly.jdk.math;

/**
 * 排序算法
 */
public class SortMath {

    /**
     * 二分排序
     * @param array
     * @return
     */
    public int[] binaryInsertSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int temp = array[i];
            int left = 0;
            int right = i - 1;
            int middle = 0;
            while (left <= right) {
                middle = (left + right) / 2;//赋值
                if (temp < array[middle]) {
                    right = middle - 1;
                } else {
                    left = middle + 1;
                }
            }
            for (int j = i - 1; j >= left; j--) {
                //从i-1到left依次向后移动一位,等待temp值插入
                array[j + 1] = array[j];
            }
            if (left != i) {
                array[left] = temp;
            }
        }
        return array;
    }

    public static void main(String[] args) {
        int[] array = {3,8,5,10,6,15,1,22};
        SortMath sortMath = new SortMath();
        int[] result = sortMath.binaryInsertSort(array);
        for(int sorted:result)
        System.out.println(sorted);
    }
}
