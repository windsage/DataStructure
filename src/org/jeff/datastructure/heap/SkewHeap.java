package org.jeff.datastructure.heap;

public class SkewHeap<T extends Comparable<T>> {

    private class SkewNode<T extends Comparable<T>> {
        public T key;
        public SkewNode<T> left;
        public SkewNode<T> right;

        public SkewNode(T key, SkewNode<T> left, SkewNode<T> right) {
            this.key = key;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return "key=" + key;
        }
    }

    private SkewNode<T> mRoot;

    private SkewNode<T> merge(SkewNode<T> x, SkewNode<T> y) {
        if (x == null) return y;
        if (y == null) return x;
        int cmp = x.key.compareTo(y.key);
        if (cmp > 0) {
            SkewNode<T> tmp = y;
            y = x;
            x = tmp;
        }
        x.right = merge(x.right, y);

        SkewNode<T> tmp = x.right;
        x.right = x.left;
        x.left = tmp;

        return x;
    }

    public void merge(SkewNode<T> node) {
        mRoot = merge(mRoot, node);
    }

    /**
     * 添加一个结点
     *
     * @param key
     */
    public void insert(T key) {
        SkewNode<T> node = new SkewNode<>(key, null, null);
        if (node != null)
            mRoot = merge(mRoot, node);
    }


    public T remove(T key) {
        SkewNode<T> node = new SkewNode<>(key, null, null);
    }

    private SkewNode<T> remove(SkewNode<T> tree) {
        if (tree == null)
            return null;
        tree = merge(tree.left, tree.right);
        return tree;
    }
}
