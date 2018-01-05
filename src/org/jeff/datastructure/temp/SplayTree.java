package org.jeff.datastructure.temp;

public class SplayTree<T extends Comparable<T>> {

    private static class SplayNode<T extends Comparable<T>> {
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

    public SplayTree(SplayNode<T> node) {
        this.mRoot = node;
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
        SplayNode<T> R = new SplayNode<>();
        SplayNode<T> L = new SplayNode<>();
        SplayNode<T> cur;
        for (; ; ) {
            int cmp = key.compareTo(tree.key);
            if (cmp < 0) {
                if (tree.left == null)
                    break;
                cmp = key.compareTo(tree.left.key);
                //在左孙子上
                if (cmp < 0) {
                    cur = tree.left;
                    tree.left = cur.right;
                    cur.right = tree;
                    tree = cur.left;
                    cur.left = R.left;
                    R.left = cur;
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
                    cur = tree.right;
                    tree.right = cur.left;
                    cur.left = tree;
                    tree = cur.right;
                    //断开和tree的联系，挂到L上去
                    cur.right = null;
                    SplayNode<T> leftBiggest = L;
                    while (leftBiggest.right != null)
                        leftBiggest = leftBiggest.right;
                    leftBiggest.right = cur;
                } else {
                    SplayNode<T> temp = tree.right;
                    tree.right = null;
                    SplayNode<T> leftBiggest = L;
                    while (leftBiggest.right != null)
                        leftBiggest = leftBiggest.right;
                    leftBiggest.right = tree;
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

    public void splay(T key) {
        mRoot = splay(mRoot, key);
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
//        int i, ilen;
//        SplayTree<Integer> tree = new SplayTree<Integer>();
//
//        System.out.print("== 依次添加: ");
//        ilen = arr.length;
//        for (i = 0; i < ilen; i++) {
//            System.out.print(arr[i] + " ");
//            tree.insert(arr[i]);
//        }
//        System.out.println();
//        tree.print();
        SplayNode<Integer> node5 = new SplayNode<>(5, null, null);
        SplayNode<Integer> node16 = new SplayNode<>(16, null, null);
        SplayNode<Integer> node18 = new SplayNode<>(18, node16, null);
        SplayNode<Integer> node13 = new SplayNode<>(13, null, null);
        SplayNode<Integer> node15 = new SplayNode<>(15, node13, node18);
        SplayNode<Integer> node24 = new SplayNode<>(24, null, null);
        SplayNode<Integer> node20 = new SplayNode<>(20, node15, node24);
        SplayNode<Integer> node30 = new SplayNode<>(30, null, null);
        SplayNode<Integer> node25 = new SplayNode<>(25, node20, node30);
        SplayNode<Integer> node12 = new SplayNode<>(12, node5, node25);

        SplayTree<Integer> tree = new SplayTree<>(node12);
        tree.print();
        tree.splay2(19);
        tree.print();
    }


    public void splay2(T key) {
        mRoot = splay2(mRoot, key);
    }


    private SplayNode<T> splay2(SplayNode<T> tree, T key) {
        if (tree == null)
            return null;
        SplayNode<T> N = new SplayNode<>();
        SplayNode<T> l = N;
        SplayNode<T> r = N;
        SplayNode<T> cur;
        int cmp;
        for (; ; ) {
            cmp = key.compareTo(tree.key);
            if (cmp < 0) {
                if (tree.left == null)
                    break;
                if (key.compareTo(tree.left.key) < 0) {
                    cur = tree.left;
                    tree.left = cur.right;
                    cur.right = tree;
                    tree = cur;
                    if (tree.left == null)
                        break;
                }
                r.left = tree;
                r = tree;
                tree = tree.left;
            } else if (cmp > 0) {
                if (tree.right == null)
                    break;
                if (key.compareTo(tree.right.key) > 0) {
                    cur = tree.right;
                    tree.right = cur.left;
                    cur.left = tree;
                    tree = cur;
                    if (tree.right == null)
                        break;
                }
                l.right = tree;
                l = tree;
                tree = tree.right;
            } else
                break;
        }
        l.right = tree.left;
        r.left = tree.right;
        tree.left = N.right;
        tree.right = N.left;
        return tree;
    }
}
