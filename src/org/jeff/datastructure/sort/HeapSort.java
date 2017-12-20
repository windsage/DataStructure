package org.jeff.datastructure.sort;

import java.util.Arrays;

/**
 * 堆排序
 */
public class HeapSort {
    private static final int[] TEST_ARRAY = {20, 40, 30, 10, 60, 50};

    /**
     * 把一个数组作为最大堆进行调整
     * @param array
     * @param start
     * @param end
     */
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

    /**
     * 1.把整个数组进行最大堆的调整，这样根结点就是最大的
     * 2.把最大的根放到数组的尾部，让前N-1个元素重复上面的最大堆调整步骤
     * @param array
     */
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
