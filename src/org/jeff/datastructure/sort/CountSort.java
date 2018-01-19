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
        for (int i = 1; i < max - min + 1; i++) {
            buckets[i] += buckets[i - 1];
        }
        int[] output = new int[a.length];
        for (int i = a.length - 1; i >= 0; i--) {
            output[buckets[a[i] - min] - 1] = a[i];
            buckets[a[i] - min]--;
        }
        for (int i = 0; i < output.length; i++) {
            a[i] = output[i];
        }
        System.out.println(Arrays.toString(a));
    }
}
