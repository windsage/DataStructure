package org.jeff.datastructure.heap;

public class LeftistHeap<T extends Comparable<T>> {

    private class LeftistNode<T extends Comparable<T>> {
        public T key;
        public LeftistNode<T> left;
        public LeftistNode<T> right;
        public int npl;


        public LeftistNode(T key, LeftistNode<T> left, LeftistNode<T> right) {
            this.key = key;
            this.left = left;
            this.right = right;
            this.npl = 0;
        }

        @Override
        public String toString() {
            return "key:" + this.key;
        }
    }


    private LeftistNode<T> mRoot;

    /**
     * 合并
     *
     * @param x
     * @param y
     * @return
     */
    private LeftistNode<T> merget(LeftistNode<T> x, LeftistNode<T> y) {
        if (x == null)
            return y;
        if (y == null)
            return x;
        //x结点比y结点大
        if (x.key.compareTo(y.key) > 0) {
            LeftistNode<T> temp = y;
            y = x;
            x = temp;
        }
        x.right = merget(x.right, y);
        if (x.left == null || x.left.npl < x.right.npl) {
            LeftistNode<T> tmp = x.right;
            x.right = x.left;
            x.left = tmp;
        }

        if (x.right == null || x.left == null)
            x.npl = 0;
        else
            x.npl = (x.left.npl < x.right.npl) ? x.left.npl + 1 : x.right.npl + 1;
        return x;
    }


    public void merge(LeftistHeap<T> other) {
        merget(mRoot, other.mRoot);
    }
}
