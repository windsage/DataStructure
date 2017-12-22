package org.jeff.datastructure.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 */
public class BubbleSort {

    private static final int[] TEST_ARRAY = {20, 40, 30, 10, 60, 50};

    /**
     * 冒泡算法第一版
     *
     * @param array
     */
    public static void bubbleSort1(int[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(array));
    }

    /**
     * 冒泡优化版
     *
     * @param array
     */
    public static void bubbleSort2(int[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            boolean flag = false;
            for (int j = 0; j < i; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    flag = true;
                }
                if (!flag)
                    break;
            }
        }
        System.out.println(Arrays.toString(array));
    }

    public static void main(String[] args) {
        BubbleSort.bubbleSort1(TEST_ARRAY);
        BubbleSort.bubbleSort2(TEST_ARRAY);
    }
}
