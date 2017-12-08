package org.jeff.datastructure.sort;

/**
 * 归并排序
 */
public class MergeSort {
    private static final int[] TEST_ARRAY = {20, 40, 30, 10, 60, 50};

    /**
     * 自上而下归并
     *
     * @param array
     * @param left
     * @param right
     */
    public static void mergeSortUp2Down(int[] array, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSortUp2Down(array, left, mid);//左边归并排序，使得左子序列有序
            mergeSortUp2Down(array, mid + 1, right);//右边归并排序，使得右子序列有序
            merge(array, left, mid, right);//将两个有序子数组合并操作
        }
    }

    /**
     * 自底向上归并
     *
     * @param array
     */
    public static void mergeSortDown2Up(int[] array) {
        if (array == null)
            return;
        for (int i = 0; i < array.length; i *= 2)
            mergeGroups(array, array.length, i);
    }

    /**
     * @param array
     * @param length 数组长度
     * @param gap    子数组长度
     */
    private static void mergeGroups(int[] array, int length, int gap) {
        int i;
    }

    /**
     * 归并方法中最重要的将两个有序的子序列合并为一个有序序列
     *
     * @param array
     * @param left
     * @param mid
     * @param right
     */
    private static void merge(int[] array, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        //第一个数组的开头
        int i = left;
        //第二个数组的开头
        int j = mid + 1;
        //temp数组的索引
        int k = 0;
        while (i <= mid && j <= right) {
            if (array[i] <= array[j]) {
                temp[k++] = array[i++];
            } else {
                temp[k++] = array[j++];
            }
        }
        while (i <= mid)
            temp[k++] = array[i++];
        while (j <= mid)
            temp[k++] = array[j++];
        //到这里temp数组已经排序完毕
        k = 0;
        while (left <= right) {
            array[left++] = temp[k++];
        }
        temp = null;
    }

    public static void main(String[] args) {
        MergeSort.mergeSortUp2Down(TEST_ARRAY, 0, 5);
        MergeSort.mergeSortDown2Up(TEST_ARRAY);
    }
}
