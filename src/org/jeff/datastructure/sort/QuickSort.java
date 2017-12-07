package org.jeff.datastructure.sort;

import java.util.Arrays;

/**
 * 快速排序
 */
public class QuickSort {
    private static final int[] TEST_ARRAY = {20, 40, 30, 10, 60, 50};


    public static void quickSort(int[] array, int left, int right) {
        if (left < right) {
            /**
             * 原理是用两个指针，一个头指针从表头开始遍历，一个尾指针从表尾开始遍历，直到头指针和尾指针指向同一个位置，
             * 那么这个位置就是当前抽取出来元素应该的归位
             *要求数组从小到大排列，取出表头的第一个元素A，那么假定索引z的位置是归位，那么把比A大的放到右侧，比A小的放到左侧
             * 所以遍历的时候，从表尾遍历的时候遇到第一个比A小的时候停止，从表头遍历的时候遇到第一个比A大的停止
             */
            int head = left;
            int tail = right;
            int x = array[head];
            while (head < tail) {
                while (head < tail && array[tail] > x)
                    tail--;
                if (head < tail) {
                    array[head] = array[tail];
                    head++;
                }
                while (head < tail && array[head] < x)
                    head++;
                if (head < tail) {
                    array[tail] = array[head];
                    tail--;
                }
            }
            array[head] = x;
            quickSort(array, left, head - 1);
            quickSort(array, head + 1, right);
            System.out.println(Arrays.toString(array));
        }
    }

    public static void main(String[] args) {
        QuickSort.quickSort2(TEST_ARRAY, 0, 5);
    }


    public static void quickSort2(int[] array, int left, int right) {
        if (left < right) {
            int head = left;
            int tail = right;
            int x = array[head];
            while (head < tail) {
                while (head < tail && array[tail] < x)
                    tail--;
                if (head < tail) {
                    array[head] = array[tail];
                    head++;
                }
                while (head < tail && array[head] > x)
                    head++;
                if (head < tail) {
                    array[tail] = array[head];
                    tail--;
                }
            }
            array[head] = x;
            quickSort2(array, left, head - 1);
            quickSort2(array, head + 1, right);
            System.out.println(Arrays.toString(array));
        }
    }
}
