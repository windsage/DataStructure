package org.jeff.datastructure.heap;

import java.util.ArrayList;
import java.util.List;

public class MinHeap<T extends Comparable<T>> {

    private List<T> mHeap;

    public MinHeap() {
        this.mHeap = new ArrayList<>();
    }

    private void filterUp(int start) {
        int c = start;
        int p = (start - 1) / 2;
        T temp = mHeap.get(c);
        while (c > 0) {
            int cmp = temp.compareTo(mHeap.get(p));
            if (cmp >= 0)
                break;
            else {
                mHeap.set(c, mHeap.get(p));
                c = p;
                p = (p - 1) / 2;
            }
        }
        mHeap.set(c, temp);
    }

    public void insert(T data) {
        mHeap.add(data);
        filterUp(mHeap.size() - 1);
    }

    private void filterDown(int start, int end) {
        int c = start;
        int left = 2 * start + 1;
        T temp = mHeap.get(c);
        while (left <= end) {
            int cmp = mHeap.get(left).compareTo(mHeap.get(left + 1));
            if (left < end && cmp > 0)
                left++;
            cmp = temp.compareTo(mHeap.get(left));
            if (cmp <= 0)
                break;
            else {
                mHeap.set(c, mHeap.get(left));
                c = left;
                left = 2 * left + 1;
            }
        }
        mHeap.set(c, temp);
    }

    private void remove(T data) {
        if (mHeap.isEmpty())
            return;
        int index = mHeap.indexOf(data);
        mHeap.set(index, mHeap.get(mHeap.size() - 1));
        mHeap.remove(mHeap.size() - 1);
        if (index < 0)
            return;
        if (mHeap.size() > 0 && index < mHeap.size())
            filterDown(index, mHeap.size() - 1);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<mHeap.size(); i++)
            sb.append(mHeap.get(i) +" ");

        return sb.toString();
    }

    public static void main(String[] args) {
        int i;
        int a[] = {80, 40, 30, 60, 90, 70, 10, 50, 20};
        MinHeap<Integer> tree=new MinHeap<Integer>();

        System.out.printf("== 依次添加: ");
        for(i=0; i<a.length; i++) {
            System.out.printf("%d ", a[i]);
            tree.insert(a[i]);
        }

        System.out.printf("\n== 最 小 堆: %s", tree);

        i=15;
        tree.insert(i);
        System.out.printf("\n== 添加元素: %d", i);
        System.out.printf("\n== 最 小 堆: %s", tree);

        i=10;
        tree.remove(i);
        System.out.printf("\n== 删除元素: %d", i);
        System.out.printf("\n== 最 小 堆: %s", tree);
        System.out.printf("\n");
    }
}
