package org.jeff.datastructure.sort;

import java.util.Arrays;

/**
 * 希尔排序
 */
public class ShellSort {
    private static final int[] TEST_ARRAY = {20, 40, 30, 10, 60, 80, 50, 70};

    public static void insertSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int x = array[i];
            int j;
            for (j = i - 1; j >= 0; j--) {
                if (array[j] < x)
                    break;
            }
            if (j != i - 1) {
                for (int k = i - 1; k > j; k--) {
                    array[k + 1] = array[k];
                }
                array[j + 1] = x;
            }
        }
        System.out.println(Arrays.toString(array));
    }


    /**
     * @param array 原数组
     * @param i     每组的起始索引
     * @param gap   步长
     */
    public static void groutSort(int[] array, int i, int gap) {
        int j;
        for (j = i + gap; j < array.length; j = j + gap) {
            //这里是插入排序
            if (array[j] < array[j - gap]) {
                int temp = array[j];
                int k = j - gap;
                while (k >= 0 && array[k] > temp) {
                    array[k + gap] = array[k];
                    k = k - gap;
                }
                array[k + gap] = temp;
            }
        }
    }

    public static void shellSort(int[] array) {
        //一开始永远是2个一组，最多有一个落单的
        for (int gap = array.length / 2; gap > 0; gap /= 2) {
            for (int i = 0; i < gap; i++) {
                groutSort(array, i, gap);
            }
        }
        System.out.println(Arrays.toString(array));
    }

    public static void main(String[] args) {
        ShellSort.shellSort(TEST_ARRAY);
    }

}
