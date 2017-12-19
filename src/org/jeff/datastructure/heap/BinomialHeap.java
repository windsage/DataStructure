package org.jeff.datastructure.heap;

public class BinomialHeap<T extends Comparable<T>> {

    private class BinomialNode<T extends Comparable<T>> {
        public T key;
        public BinomialNode<T> child;
        public BinomialNode<T> parent;
        public int degree;
        public BinomialNode<T> next;

        public BinomialNode(T key) {
            this.key = key;
            this.child = null;
            this.parent = null;
            this.degree = 0;
            this.next = null;
        }

        @Override
        public String toString() {
            return "key=" + key;
        }
    }


    private BinomialNode<T> merge(BinomialNode<T> h1, BinomialNode<T> h2) {
        if (h1 == null) return h2;
        if (h2 == null) return h1;
        // root是新堆的根，h3用来遍历h1和h3的。
        BinomialNode<T> pre_h3 = null;
        BinomialNode<T> h3 = null;
        BinomialNode<T> root = null;
        while ((h1 != null) && (h2 != null)) {
            if (h1.degree <= h2.degree) {
                h3 = h1;
                h1 = h1.next;
            } else {
                h3 = h2;
                h2 = h2.next;
            }

            if (pre_h3 == null) {
                pre_h3 = h3;
                root = h3;
            } else {
                pre_h3.next = h3;
                pre_h3  = h3;
            }
            //这里不懂
            if (h1 != null){
                h3.next = h1;
            }else {
                h3.next = h2;
            }
        }
    }


}
