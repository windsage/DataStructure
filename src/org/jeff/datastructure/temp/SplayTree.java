package org.jeff.datastructure.temp;

public class SplayTree<T extends Comparable<T>> {

    private class SplayNode<T extends Comparable<T>> {
        T key;
        SplayNode<T> left;
        SplayNode<T> right;

        public SplayNode(T key, SplayNode<T> left, SplayNode<T> right) {
            this.key = key;
            this.left = left;
            this.right = right;
        }
    }

    private SplayNode<T> mRoot;

    public SplayTree() {
        this.mRoot = null;
    }

    private SplayNode<T> insert(SplayNode<T> tree, SplayNode<T> z) {
        SplayNode<T> x = tree;
        SplayNode<T> y = null;
        int cmp;
        while (x != null) {
            y = x;
            cmp = z.key.compareTo(tree.key);
            if (cmp > 0) {
                x = x.right;
            } else if (cmp < 0) {
                x = x.left;
            } else {
                return tree;
            }
        }
        if (y == null)
            tree = z;
        else {
            cmp = z.key.compareTo(y.key);
            if (cmp > 0) {
                y.right = z;
            } else
                y.left = z;
        }
        return tree;

    }


    private SplayNode<T> search(SplayNode<T> tree, T key) {
        SplayNode<T> node = tree;
        int cmp;
        while (node != null) {
            cmp = key.compareTo(node.key);
            if (cmp < 0)
                node = node.left;
            else if (cmp > 0)
                node = node.right;
            else
                return node;
        }
        return node;
    }

    private SplayNode<T> search2(SplayNode<T> tree, T key) {
        int cmp = key.compareTo(tree.key);
        if (cmp < 0)
            return search2(tree.left, key);
        else if (cmp > 0)
            return search2(tree.right, key);
        else
            return tree;
    }

    private SplayNode<T> splay(SplayNode<T> tree, T key) {
        if (tree == null)
            return tree;
        SplayNode<T> cur;
        for (; ; ) {
            int cmp = key.compareTo(tree.key);
            if (cmp < 0) {
                cmp = key.compareTo(tree.left.key);
                if (cmp > 0) {
                    //zig-zag
                    cur = tree.left;
                    tree.left = cur.right;
                    cur.right = tree;
                    tree = cur;
                } else if (cmp < 0) {
                    //zig-zig
                }
            } else if (cmp > 0) {
                cmp = key.compareTo(tree.right.key);
                if (cmp > 0) {
                    //zig-zig
                } else if (cmp < 0) {
                    //zig-zag
                    cur = tree.right;
                    tree.right = cur.left;
                    cur.left = tree;
                    tree = cur;
                }
            } else {

            }
        }
    }


}
