package org.jeff.datastructure.heap;

import java.util.ArrayList;
import java.util.List;

public class MaxHeap<T extends Comparable<T>> {

    private List<T> mHeap;

    public MaxHeap() {
        this.mHeap = new ArrayList<>();
    }

    protected void filterup(int start) {
        int c = start;
        int parent = (c - 1) / 2;
        T temp = mHeap.get(c);
        //二叉堆是一个数组
        while (c > 0) {
            int cmp = temp.compareTo(mHeap.get(parent));
            if (cmp < 0) {
                break;
            } else {
                mHeap.set(c, mHeap.get(parent));
                c = parent;
                parent = (parent - 1) / 2;
            }
        }
        mHeap.set(c, temp);
    }

    public void insert(T data) {
        int size = mHeap.size();
        mHeap.add(data);
        filterup(size);
    }


    private void filterdown(int start, int end) {
        int c = start;
        int left = 2 * c + 1;
        T temp = mHeap.get(c);
        while (left <= end) {
            int cmp = mHeap.get(left).compareTo(mHeap.get(left + 1));
            //找出左右孩子中的最大值,left就是最大值的索引
            if (left < end && cmp < 0)
                left++;
            //将最大值和temp进行比较
            cmp = temp.compareTo(mHeap.get(left));
            if (cmp >= 0) {
                break;
            } else {
                //把c这个位置放入较大的值，c就变到left位置上，求出left的左孩子，看是否数组越界
                mHeap.set(c, mHeap.get(left));
                c = left;
                left = 2 * left + 1;
            }
        }
        mHeap.set(c, temp);
    }

    public int remove(T data) {
        if (mHeap.isEmpty())
            return -1;
        int index = mHeap.indexOf(data);
        if (index == -1)
            return -1;
        int size = mHeap.size();
        mHeap.set(index, mHeap.get(size - 1));
        mHeap.remove(size - 1);
        if (mHeap.size() > 1 && index < mHeap.size())
            filterdown(index, mHeap.size() - 1);
        return 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < mHeap.size(); i++)
            sb.append(mHeap.get(i) + " ");

        return sb.toString();
    }

    public static void main(String[] args) {
        int i;
        int a[] = {10, 40, 30, 60, 90, 70, 20, 50, 80};
        MaxHeap<Integer> tree = new MaxHeap<Integer>();

        System.out.printf("== 依次添加: ");
        for (i = 0; i < a.length; i++) {
            System.out.printf("%d ", a[i]);
            tree.insert(a[i]);
        }

        System.out.printf("\n== 最 大 堆: %s", tree);

        i = 85;
        tree.insert(i);
        System.out.printf("\n== 添加元素: %d", i);
        System.out.printf("\n== 最 大 堆: %s", tree);

        i = 90;
        tree.remove(i);
        System.out.printf("\n== 删除元素: %d", i);
        System.out.printf("\n== 最 大 堆: %s", tree);
        System.out.printf("\n");
    }

}
