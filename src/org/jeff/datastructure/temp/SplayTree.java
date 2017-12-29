package org.jeff.datastructure.temp;

public class SplayTree<T extends Comparable<T>> {

    private class SplayNode<T extends Comparable<T>> {
        T key;
        SplayNode<T> left;
        SplayNode<T> right;

        public SplayNode() {
            this.left = null;
            this.right = null;
        }

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

    private void insert(T key) {
        SplayNode<T> z = new SplayNode<>(key, null, null);
        if (z == null)
            return;
        mRoot = insert(mRoot, z);
        mRoot = splay(mRoot, key);
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

    //http://www.cppblog.com/MiYu/archive/2010/11/12/133405.html
    private SplayNode<T> splay(SplayNode<T> tree, T key) {
        if (tree == null)
            return tree;
        SplayNode<T> R = null;
        SplayNode<T> L = null;
        for (; ; ) {
            int cmp = key.compareTo(tree.key);
            if (cmp < 0) {
                if (tree.left == null)
                    break;
                cmp = key.compareTo(tree.left.key);
                //在左孙子上
                if (cmp < 0) {
                    SplayNode<T> temp = tree.left;
                    tree.left = temp.right;
                    temp.right = tree;
                    tree = temp.left;
                    if (R == null) {
                        temp.left = null;
                        R = temp;
                    } else {
                        temp.left = R.left;
                    }

                    R = temp;
                } else {
                    SplayNode<T> temp = tree.left;
                    tree.left = R.left;
                    R.left = tree;
                    tree = temp;
                }
            } else if (cmp > 0) {
                if (tree.right == null)
                    break;
                cmp = key.compareTo(tree.right.key);
                if (cmp > 0) {
                    SplayNode<T> temp = tree.right;
                    tree.right = temp.left;
                    temp.left = tree;
                    tree = temp.right;
                    temp.right = L.right;
                    L.right = temp;
                } else {
                    SplayNode<T> temp = tree.right;
                    tree.right = L.right;
                    L.right = tree;
                    tree = temp;
                }
            } else {
                break;
            }
        }
        SplayNode<T> A = tree.left;
        SplayNode<T> B = tree.right;
        SplayNode<T> tmp;
        tmp = L;
        while (tmp.right != null)
            tmp = tmp.right;
        tmp.right = A;
        tmp = R;
        while (tmp.left != null)
            tmp = tmp.left;
        tmp.left = B;
        tree.right = R.left;
        tree.left = L.right;
        return tree;
    }

    private void print(SplayNode<T> tree, T key, int direction) {

        if (tree != null) {

            if (direction == 0)    // tree是根节点
                System.out.printf("%2d is root\n", tree.key);
            else                // tree是分支节点
                System.out.printf("%2d is %2d's %6s child\n", tree.key, key, direction == 1 ? "right" : "left");

            print(tree.left, tree.key, -1);
            print(tree.right, tree.key, 1);
        }
    }

    public void print() {
        if (mRoot != null)
            print(mRoot, mRoot.key, 0);
    }

    private static final int arr[] = {10, 50, 40, 30, 20, 60};

    public static void main(String[] args) {
        int i, ilen;
        SplayTree<Integer> tree = new SplayTree<Integer>();

        System.out.print("== 依次添加: ");
        ilen = arr.length;
        for (i = 0; i < ilen; i++) {
            System.out.print(arr[i] + " ");
            tree.insert(arr[i]);
        }
        System.out.println();
        tree.print();
    }
}
