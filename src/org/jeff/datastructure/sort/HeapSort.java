package org.jeff.datastructure.sort;

import java.util.Arrays;

/**
 * 堆排序
 */
public class HeapSort {
    private static final int[] TEST_ARRAY = {20, 40, 30, 10, 60, 50};

    public static void maxHeap(int[] array, int start, int end) {
        int c = start;
        int left = 2 * c + 1;//左孩子的索引
        int temp = array[c];
        for (; left <= end; c = left, left = 2 * c + 1) {
            if (left < end && array[left] < array[left + 1])
                left++;
            if (temp >= array[left])
                break;
            else {
                array[c] = array[left];
                array[left] = temp;
            }
        }
    }

    public static void headSort(int[] array) {
        int i;
        for (i = array.length / 2 - 1; i >= 0; i--) {
            maxHeap(array, i, array.length - 1);
        }
        for (i = array.length - 1; i > 0; i--) {
            int temp = array[i];
            array[i] = array[0];
            array[0] = temp;
            maxHeap(array, 0, i - 1);
        }
        System.out.println(Arrays.toString(array));
    }

    public static void main(String[] args) {
        HeapSort.headSort(TEST_ARRAY);
    }
}
