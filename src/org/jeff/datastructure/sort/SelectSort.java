package org.jeff.datastructure.sort;

import java.util.Arrays;

/**
 * 选择排序
 */
public class SelectSort {
    private static final int[] TEST_ARRAY = {20, 40, 30, 10, 60, 50};

    public static void selectSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int min = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[min] > array[j]) {
                    min = j;
                }
                if (min != i) {
                    int temp = array[i];
                    array[i] = array[min];
                    array[min] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(array));
    }


    public static void main(String[] args) {
        SelectSort.selectSort(TEST_ARRAY);
    }
}
