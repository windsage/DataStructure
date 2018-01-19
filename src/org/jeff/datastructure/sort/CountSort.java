package org.jeff.datastructure.sort;

import java.util.Arrays;

/**
 * 计数排序
 */
public class CountSort {
    private static final int[] TEST_ARRAY = {20, 40, 30, 10, 60, 50};

    public static void main(String[] args) {
        CountSort sort = new CountSort();
        sort.countSort(TEST_ARRAY);
    }


    private void countSort(int[] a) {
        int min = a[0];
        int max = a[0];
        //找出最大值和最小值
        for (int i : a) {
            if (min > i)
                min = i;
            if (max < i)
                max = i;
        }
        int[] buckets = new int[max - min + 1];
        for (int i : a) {
            buckets[i - min]++;
        }
        int index = 0;
        for (int i = 0; i < buckets.length; i++) {
            while (buckets[i] > 0) {
                a[index++] = min + i;
                buckets[i]--;
            }
        }
        System.out.println(Arrays.toString(a));
    }
}
