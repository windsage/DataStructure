package org.jeff.datastructure.sort;

/**
 * 基数排序
 */
public class RadixSort {
    private static final int[] TEST_ARRAY = {20, 40, 30, 10, 60, 50};

    private static void bucketSort(int[] array) {
        int length = array.length;
        int[] buckets = new int[10];
        for (int i = 0; i < length; i++) {
            buckets[array[i]]++;
        }
        for (int i = 0, j = 0; i < 10; i++) {
            while ((buckets[i]--) > 0) {
                array[j++] = i;
            }
        }
    }
}
