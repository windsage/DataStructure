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

    public SkewHeap() {
        mRoot = null;
    }

    /*
     * 前序遍历"斜堆"
     */
    private void preOrder(SkewNode<T> heap) {
        if (heap != null) {
            System.out.print(heap.key + " ");
            preOrder(heap.left);
            preOrder(heap.right);
        }
    }

    public void preOrder() {
        preOrder(mRoot);
    }

    /*
     * 中序遍历"斜堆"
     */
    private void inOrder(SkewNode<T> heap) {
        if (heap != null) {
            inOrder(heap.left);
            System.out.print(heap.key + " ");
            inOrder(heap.right);
        }
    }

    public void inOrder() {
        inOrder(mRoot);
    }

    /*
     * 后序遍历"斜堆"
     */
    private void postOrder(SkewNode<T> heap) {
        if (heap != null) {
            postOrder(heap.left);
            postOrder(heap.right);
            System.out.print(heap.key + " ");
        }
    }

    public void postOrder() {
        postOrder(mRoot);
    }

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


    public T remove() {
        if (this.mRoot == null)
            return null;

        T key = this.mRoot.key;
        SkewNode<T> l = this.mRoot.left;
        SkewNode<T> r = this.mRoot.right;

        this.mRoot = null;          // 删除根节点
        this.mRoot = merge(l, r);   // 合并左右子树

        return key;
    }

    /*
     * 销毁斜堆
     */
    private void destroy(SkewNode<T> heap) {
        if (heap == null)
            return;

        if (heap.left != null)
            destroy(heap.left);
        if (heap.right != null)
            destroy(heap.right);

        heap = null;
    }

    public void clear() {
        destroy(mRoot);
        mRoot = null;
    }

    /*
     * 打印"斜堆"
     *
     * key        -- 节点的键值
     * direction  --  0，表示该节点是根节点;
     *               -1，表示该节点是它的父结点的左孩子;
     *                1，表示该节点是它的父结点的右孩子。
     */
    private void print(SkewNode<T> heap, T key, int direction) {
        if (heap != null) {
            if (direction == 0)    // heap是根节点
                System.out.printf("%2d is root\n", heap.key);
            else                // heap是分支节点
                System.out.printf("%2d is %2d's %6s child\n", heap.key, key, direction == 1 ? "right" : "leftChild");

            print(heap.left, heap.key, -1);
            print(heap.right, heap.key, 1);
        }
    }

    public void print() {
        if (mRoot != null)
            print(mRoot, mRoot.key, 0);
    }
}
