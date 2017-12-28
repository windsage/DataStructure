package org.jeff.datastructure.binarytree;

public class SplayTree<T extends Comparable<T>> {

    private class SplayTreeNode<T extends Comparable<T>> {
        public T key;
        public SplayTreeNode<T> left;
        public SplayTreeNode<T> right;

        public SplayTreeNode() {
            this.left = null;
            this.right = null;
        }

        public SplayTreeNode(T key, SplayTreeNode<T> left, SplayTreeNode<T> right) {
            this.key = key;
            this.left = left;
            this.right = right;
        }
    }

    // 根结点
    private SplayTreeNode<T> mRoot;

    public SplayTree() {
        mRoot = null;
    }

    /*
     * 前序遍历"伸展树"
     */
    private void preOrder(SplayTreeNode<T> tree) {
        if (tree != null) {
            System.out.print(tree.key + " ");
            preOrder(tree.left);
            preOrder(tree.right);
        }
    }

    public void preOrder() {
        preOrder(mRoot);
    }

    /*
     * 中序遍历"伸展树"
     */
    private void inOrder(SplayTreeNode<T> tree) {
        if (tree != null) {
            inOrder(tree.left);
            System.out.print(tree.key + " ");
            inOrder(tree.right);
        }
    }

    public void inOrder() {
        inOrder(mRoot);
    }


    /*
     * 后序遍历"伸展树"
     */
    private void postOrder(SplayTreeNode<T> tree) {
        if (tree != null) {
            postOrder(tree.left);
            postOrder(tree.right);
            System.out.print(tree.key + " ");
        }
    }

    public void postOrder() {
        postOrder(mRoot);
    }


    /*
     * (递归实现)查找"伸展树x"中键值为key的节点
     */
    private SplayTreeNode<T> search(SplayTreeNode<T> x, T key) {
        if (x == null)
            return x;

        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            return search(x.left, key);
        else if (cmp > 0)
            return search(x.right, key);
        else
            return x;
    }

    public SplayTreeNode<T> search(T key) {
        return search(mRoot, key);
    }

    /*
     * (非递归实现)查找"伸展树x"中键值为key的节点
     */
    private SplayTreeNode<T> iterativeSearch(SplayTreeNode<T> x, T key) {
        while (x != null) {
            int cmp = key.compareTo(x.key);

            if (cmp < 0)
                x = x.left;
            else if (cmp > 0)
                x = x.right;
            else
                return x;
        }

        return x;
    }

    public SplayTreeNode<T> iterativeSearch(T key) {
        return iterativeSearch(mRoot, key);
    }

    /*
     * 查找最小结点：返回tree为根结点的伸展树的最小结点。
     */
    private SplayTreeNode<T> minimum(SplayTreeNode<T> tree) {
        if (tree == null)
            return null;

        while (tree.left != null)
            tree = tree.left;
        return tree;
    }

    public T minimum() {
        SplayTreeNode<T> p = minimum(mRoot);
        if (p != null)
            return p.key;

        return null;
    }

    /*
     * 查找最大结点：返回tree为根结点的伸展树的最大结点。
     */
    private SplayTreeNode<T> maximum(SplayTreeNode<T> tree) {
        if (tree == null)
            return null;

        while (tree.right != null)
            tree = tree.right;
        return tree;
    }

    public T maximum() {
        SplayTreeNode<T> p = maximum(mRoot);
        if (p != null)
            return p.key;

        return null;
    }

    /**
     * 旋转
     *
     * @param tree
     * @param key
     * @return
     */
    private SplayTreeNode<T> splay(SplayTreeNode<T> tree, T key) {
        if (tree == null)
            return tree;

        SplayTreeNode<T> N = new SplayTreeNode<>();
        SplayTreeNode<T> l = N;
        SplayTreeNode<T> r = N;
        SplayTreeNode<T> c;
        for (; ; ) {
            int cmp = key.compareTo(tree.key);
            if (cmp < 0) {
                //在左子树上
                if (tree.left == null)
                    break;
                if (key.compareTo(tree.left.key) < 0) {
                    c = tree.left;
                    tree.left = c.right;
                    c.right = tree;
                    tree = c;
                    if (tree.left == null)
                        break;
                }
                //这里是link right
                r.left = tree;
                r = tree;
                tree = tree.left;
            }
            else if (cmp > 0) {
                if (tree.right == null)
                    break;
                if (key.compareTo(tree.right.key) > 0) {
                    //这里是一个类似AVL树RR旋转的过程
                    c = tree.right;
                    tree.right = c.left;
                    c.left = tree;
                    //这里就完成了一次旋转，形成新的tree
                    tree = c;
                    //判断tree是否有右结点，没有的话就旋转结束了
                    if (tree.right == null)
                        break;
                }

                //这里是一个link left的过程，在最上的结点上新增一个空结点，让这个空结点成为tree,
                l.right = tree;
                l = tree;
                tree = tree.right;
            }
            else
                break;
        }
        //这里是组合的过程，这里不懂
        l.right = tree.left;
        r.left = tree.right;
        tree.left = N.right;
        tree.right = N.left;
        return tree;
    }

    public void splay(T key) {
        mRoot = splay(mRoot, key);
    }


    /*
     * 将结点插入到伸展树中，并返回根节点
     *
     * 参数说明：
     *     tree 伸展树的
     *     z 插入的结点
     */
    private SplayTreeNode<T> insert(SplayTreeNode<T> tree, SplayTreeNode<T> z) {
        int cmp;
        SplayTreeNode<T> y = null;
        SplayTreeNode<T> x = tree;

        // 查找z的插入位置
        while (x != null) {
            y = x;
            cmp = z.key.compareTo(x.key);
            if (cmp < 0)
                x = x.left;
            else if (cmp > 0)
                x = x.right;
            else {
                System.out.printf("不允许插入相同节点(%d)!\n", z.key);
                z = null;
                return tree;
            }
        }

        if (y == null)
            tree = z;
        else {
            cmp = z.key.compareTo(y.key);
            if (cmp < 0)
                y.left = z;
            else
                y.right = z;
        }

        return tree;
    }


    private SplayTreeNode<T> insert2(SplayTreeNode<T> tree, SplayTreeNode<T> z) {
        if (tree == null)
            return z;
        int cmp = z.key.compareTo(tree.key);
        if (cmp < 0)
            tree.left = insert2(tree.left, z);
        else if (cmp > 0)
            tree.right = insert2(tree.right, z);
        else {
            System.out.println("不允许插入相同结点");
            return tree;
        }
        return tree;
    }

    public void insert(T key) {
        SplayTreeNode<T> z = new SplayTreeNode<T>(key, null, null);

        // 如果新建结点失败，则返回。
        if ((z = new SplayTreeNode<T>(key, null, null)) == null)
            return;

        // 插入节点
        mRoot = insert(mRoot, z);
        // 将节点(key)旋转为根节点
        mRoot = splay(mRoot, key);
    }

    public void insert2(T key) {
        SplayTreeNode<T> z = new SplayTreeNode<T>(key, null, null);

        // 如果新建结点失败，则返回。
        if ((z = new SplayTreeNode<T>(key, null, null)) == null)
            return;

        // 插入节点
        mRoot = insert2(mRoot, z);
        // 将节点(key)旋转为根节点
        mRoot = splay(mRoot, key);
    }


    /*
     * 删除结点(z)，并返回被删除的结点
     *
     * 参数说明：
     *     bst 伸展树
     *     z 删除的结点
     */
    private SplayTreeNode<T> remove(SplayTreeNode<T> tree, T key) {
        SplayTreeNode<T> x;

        if (tree == null)
            return null;

        // 查找键值为key的节点，找不到的话直接返回。
        if (search(tree, key) == null)
            return tree;

        // 将key对应的节点旋转为根节点。
        tree = splay(tree, key);

        if (tree.left != null) {
            // 将"tree的前驱节点"旋转为根节点
            x = splay(tree.left, key);
            // 移除tree节点
            x.right = tree.right;
        } else
            x = tree.right;

        tree = null;

        return x;
    }

    public void remove(T key) {
        mRoot = remove(mRoot, key);
    }

    /*
     * 销毁伸展树
     */
    private void destroy(SplayTreeNode<T> tree) {
        if (tree == null)
            return;

        if (tree.left != null)
            destroy(tree.left);
        if (tree.right != null)
            destroy(tree.right);

        tree = null;
    }

    public void clear() {
        destroy(mRoot);
        mRoot = null;
    }

    /*
     * 打印"伸展树"
     *
     * key        -- 节点的键值
     * direction  --  0，表示该节点是根节点;
     *               -1，表示该节点是它的父结点的左孩子;
     *                1，表示该节点是它的父结点的右孩子。
     */
    private void print(SplayTreeNode<T> tree, T key, int direction) {

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

    private static final int arr[] = {10,50,40,30,20,60};

    public static void main(String[] args) {
        int i, ilen;
        SplayTree<Integer> tree=new SplayTree<Integer>();

        System.out.print("== 依次添加: ");
        ilen = arr.length;
        for(i=0; i<ilen; i++) {
            System.out.print(arr[i]+" ");
            tree.insert(arr[i]);
        }

        System.out.print("\n== 前序遍历: ");
        tree.preOrder();

        System.out.print("\n== 中序遍历: ");
        tree.inOrder();

        System.out.print("\n== 后序遍历: ");
        tree.postOrder();
        System.out.println();

        System.out.println("== 最小值: "+ tree.minimum());
        System.out.println("== 最大值: "+ tree.maximum());
        System.out.println("== 树的详细信息: ");
        tree.print();

        i = 30;
        System.out.printf("\n== 旋转节点(%d)为根节点\n", i);
        tree.splay(i);
        System.out.printf("== 树的详细信息: \n");
        tree.print();

        // 销毁二叉树
        tree.clear();
    }
}
