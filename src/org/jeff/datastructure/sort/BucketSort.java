package org.jeff.datastructure.sort;

import java.util.Arrays;

/**
 * 桶排序
 */
public class BucketSort {
    private static final int[] TEST_ARRAY = {20, 40, 30, 10, 60, 50};


    public static void bucketSort(int[] array, int max) {
        int length = array.length;
        int[] buckets = new int[max];
        for (int i = 0; i < length; i++) {
            buckets[array[i]]++;
        }
        for (int i = 0, j = 0; i < max; i++) {
            int key = buckets[i];
            while ((key--) > 0) {
                array[j] = i;
                j++;
            }
        }
        System.out.println(Arrays.toString(array));
    }

    public static void main(String[] args) {
        BucketSort.bucketSort(TEST_ARRAY, 70);
    }
}
