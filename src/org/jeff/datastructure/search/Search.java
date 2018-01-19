package org.jeff.datastructure.search;

/**
 *二叉查找
 */
public class Search {

    public static void main(String[] args) {

    }


    private int sequenceSearch(int[] a, int value) {
        for (int i = 0; i < a.length; i++) {
            if (value == a[i])
                return i;
        }
        return -1;
    }

    private int binarySearch(int[] a, int value) {
        int low = 0, high = a.length - 1;
        int mid = (low + high) >> 1;
        while (low <= high) {
            if (a[mid] == value) {
                return mid;
            }
            if (a[mid] < value) {
                low = mid + 1;
            }
            if (a[mid] > value) {
                high = mid - 1;
            }
        }
        return -1;
    }

    private int binarySearch(int[] a, int value, int low, int high) {
        int mid = (low + high) >> 1;
        if (a[mid] == value) {
            return mid;
        }
        if (a[mid] < value) {
            return binarySearch(a, value, mid + 1, high);
        }
        if (a[mid] > value) {
            return binarySearch(a, value, low, mid - 1);
        }
        return -1;
    }


    private int fibonacciSearch(int[] a, int value) {
        int[] f = fibonacci(a.length);
        int k = 0;
        int high = a.length - 1;
        int low = 0;
        int mid = 0;
        while (a.length > f[k] - 1)
            k++;
        int[] tmp = new int[f[k] - 1];
        for (int i = 0; i < a.length; i++) {
            tmp[i] = a[i];
        }
        for (int i = a.length; i < f[k] - 1; i++) {
            tmp[i] = a[high];
        }
        while (low <= high) {
            mid = low + f[k - 1] - 1;

            if (tmp[mid] < value) {
                low = mid + 1;
                k = k - 2;
            } else if (tmp[mid] > value) {
                high = mid - 1;
                k = k - 1;
            } else {
                if (mid <= high)
                    return mid;
                else
                    return high;
            }
        }
        return -1;
    }

    private int[] fibonacci(int n) {
        int[] f = new int[n];
        f[0] = 0;
        f[1] = 1;
        for (int i = 2; i < n; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }
}
