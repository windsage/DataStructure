package org.jeff.datastructure.heap;

public class BinomialHeap<T extends Comparable<T>> {
    private class BinomialNode<T extends Comparable<T>> {
        T key;
        int degree;
        BinomialNode<T> leftChild;
        BinomialNode<T> parent;
        BinomialNode<T> next;

        BinomialNode(T key) {
            this.key = key;
            this.degree = 0;
            this.leftChild = null;
            this.parent = null;
            this.next = null;
        }

        @Override
        public String toString() {
            return "key=" + key;
        }
    }

    private BinomialNode<T> mRoot;

    public BinomialHeap() {
        this.mRoot = null;
    }

    /**
     * 找出最小值
     *
     * @return
     */
    public T minium() {
        if (mRoot == null)
            return null;
        BinomialNode<T> x, y;
        //x用来遍历，y用来存储，打擂台算法
        x = mRoot;
        y = mRoot;
        while (x.next != null) {
            if (x.key.compareTo(y.key) < 0) {
                y = x;
            }
            x = x.next;
        }
        return y.key;
    }


    /**
     * 合并两个二项堆
     *
     * @param h1
     * @param h2
     * @return
     */
    private BinomialNode<T> merge(BinomialNode<T> h1, BinomialNode<T> h2) {
        if (h1 == null) return h2;
        if (h2 == null) return h1;
        BinomialNode<T> h3;
        BinomialNode<T> pre_h3 = null;
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
                pre_h3 = h3;
            }
            //这里还需要思考
            if (h1 != null) {
                h3.next = h1;
            } else {
                h3.next = h2;
            }
        }
        return root;
    }


    /**
     * 将较大的二项树作为child
     *
     * @param child
     * @param root
     */
    private void link(BinomialNode<T> child, BinomialNode<T> root) {
        child.parent = root;
        child.next = root.leftChild;
        root.leftChild = child;
        root.degree++;
    }

    /**
     * 合并两个二项堆
     *
     * @param h1
     * @param h2
     * @return
     */
    public BinomialNode<T> union(BinomialNode<T> h1, BinomialNode<T> h2) {
        BinomialNode<T> root;
        root = merge(h1, h2);
        BinomialNode<T> prev_x = null;
        BinomialNode<T> x = root;
        BinomialNode<T> next_x = x.next;
        while (next_x != null) {
            //x.degree != next_x.degree != next_x.next.degree
            if (x.degree != next_x.degree || (next_x.next != null && next_x.degree != next_x.next.degree)) {
                prev_x = x;
                x = next_x;
            } else if (x.key.compareTo(next_x.key) < 0) {
                //x.key < next_x.key
                x.next = next_x.next;
                link(next_x, x);
            } else {
                //x.key > next_x.key
                //没有prev_x
                if (prev_x == null) {
                    root = next_x;
                } else {
                    prev_x.next = next_x;
                }
                link(x, next_x);
                x = next_x;
            }
            next_x = next_x.next;
        }
        return root;
    }

    /**
     * 将一个二项堆合并进入当前的二项堆中
     *
     * @param heap
     */
    public void union(BinomialHeap<T> heap) {
        if (mRoot != null && heap.mRoot != null)
            union(mRoot, heap.mRoot);
    }

    private BinomialNode<T> search(BinomialNode<T> root, T key) {
        if (root == null)
            return null;
        while (root.next != null) {
            if (key.compareTo(root.key) == 0)
                return root;
            else {
                BinomialNode<T> child;
                if ((child = search(root.leftChild, key)) != null)
                    return child;
                root = root.next;
            }
        }
        return null;
    }

    public boolean contains(T key) {
        return search(mRoot, key) != null;
    }

    /**
     * 插入一个结点
     *
     * @param key
     */
    public void insert(T key) {
        BinomialNode<T> node = new BinomialNode<>(key);
        if (contains(key))
            System.out.println("insert failed");
        mRoot = union(mRoot, node);
    }

}
