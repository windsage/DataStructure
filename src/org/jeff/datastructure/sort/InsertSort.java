package org.jeff.datastructure.sort;

import java.util.Arrays;

/**
 * 插入排序
 */
public class InsertSort {
    private static final int[] TEST_ARRAY = {20, 40, 30, 10, 60, 50};

    public static void insertSort(int[] array) {
        int pos;
        //从索引1开始到n-1进行遍历,每个元素往前插入
        for (int i = 1; i < array.length; i++) {
            //反向遍历前i-1个元素找到第一个比array[i]小的元素，并且记住这个位置，
            // 这个位置的下一个位置就是array[i]需要插入的位置
            for (pos = i - 1; pos >= 0; pos--) {
                if (array[pos] < array[i])
                    break;
            }
            //pos最大为i-1,当pos=i+1的时候，array[i]就在原来位置上不用动
            if (pos != i - 1) {
                //记住array[i]的值
                int temp = array[i];
                //从i-1开始到pos后一个索引，每个索引往后移动一个位置
                for (int k = i - 1; k > pos; k--) {
                    array[k + 1] = array[k];
                }
                array[pos + 1] = temp;
            }
        }
        System.out.println(Arrays.toString(array));
    }

    public static void main(String[] args) {
        InsertSort.insertSort(TEST_ARRAY);
    }
}
