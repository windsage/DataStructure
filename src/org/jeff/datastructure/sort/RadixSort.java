package org.jeff.datastructure.sort;

import java.util.Arrays;

/**
 * 基数排序
 */
public class RadixSort {
    private static final int[] TEST_ARRAY = {53, 3, 542, 748, 14, 214, 154, 63, 616};

    /**
     * 获取最大值
     *
     * @param array
     * @return
     */
    private static int getMax(int[] array) {
        int max = 0;
        for (int i = 0; i < array.length; i++) {
            if (max < array[i]) {
                max = array[i];
            }
        }
        return max;
    }

    private static void countSort(int[] array, int exp, int length) {
        int[] output = new int[length];
        int[] buckets = new int[10];
        for (int i = 0; i < length; i++) {
            //取出最后一位
            int last = (array[i] / exp) % 10;
            buckets[last]++;
        }
        //表示array数组中 尾数小于等于 buckets某位置上值 的个数，这样从头到位才能把所有的数据连起来
        for (int i = 1; i < 10; i++) {
            buckets[i] += buckets[i - 1];
        }
        for (int i = array.length - 1; i >= 0; i--) {
            int last = (array[i] / exp) % 10;
            output[buckets[last] - 1] = array[i];
            buckets[last]--;
        }
        for (int i = 0; i < length; i++)
            array[i] = output[i];
    }

    public static void radixSort(int[] array) {
        int max = getMax(array);
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countSort(array, exp, array.length);
        }
        System.out.println(Arrays.toString(array));
    }

    public static void main(String[] args) {
        RadixSort.radixSort(TEST_ARRAY);
    }
}
