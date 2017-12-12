package org.jeff.datastructure.binarytree;

public class AVLTree<T extends Comparable<T>> {

    /**
     * 二叉平衡树结点
     *
     * @param <T>
     */
    private class AVLTreeNode<T> {
        public AVLTreeNode<T> left;//左孩子
        public AVLTreeNode<T> right;//右孩子
        public int height;
        public T key;

        public AVLTreeNode(T key, AVLTreeNode<T> left, AVLTreeNode<T> right) {
            this.left = left;
            this.right = right;
            this.height = 0;
            this.key = key;
        }
    }

    //根，类比链表中的head
    private AVLTreeNode<T> mRoot;

    public AVLTree() {
        this.mRoot = null;
    }

    /**
     * 获取某树的高度
     *
     * @return
     */
    private int height(AVLTreeNode<T> tree) {
        return tree != null ? tree.height : 0;
    }

    /**
     * 用于比较左右两棵子树的高度大小
     *
     * @param a
     * @param b
     * @return
     */
    private int max(int a, int b) {
        return a > b ? a : b;
    }

    /**
     * LL旋转
     *
     * @param k2 最小不平衡树
     * @return
     */
    private AVLTreeNode<T> leftLeftRotation(AVLTreeNode<T> k2) {
        AVLTreeNode<T> k1;
        k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k1.height = max(k1.left.height, k1.right.height) + 1;
        k2.height = max(k2.left.height, k2.right.height) + 1;
        return k1;
    }

    /**
     * LR旋转
     *
     * @param k3
     * @return
     */
    private AVLTreeNode<T> leftRightRotation(AVLTreeNode<T> k3) {
        k3.left = rightRightRotation(k3);
        return leftLeftRotation(k3);
    }


    /**
     * RR旋转
     *
     * @param k2
     * @return
     */
    private AVLTreeNode<T> rightRightRotation(AVLTreeNode<T> k2) {
        AVLTreeNode<T> k1;
        k1 = k2.right;
        k2.right = k1.left;
        k1.left = k2.right;
        k1.height = max(k1.left.height, k1.right.height) + 1;
        k2.height = max(k2.left.height, k2.right.height) + 1;
        return k1;
    }

    /**
     * RL 旋转
     *
     * @param k1
     * @return
     */
    private AVLTreeNode<T> rightLeftRotation(AVLTreeNode<T> k1) {
        k1.right = rightRightRotation(k1);
        return leftLeftRotation(k1);
    }

    /*
     * 私有方法，插入给一棵树插入一个值
     *
     * @param tree
     * @param key
     * @return
     */
    private AVLTreeNode<T> insert(AVLTreeNode<T> tree, T key) {
        if (tree == null) {
            tree = new AVLTreeNode<T>(key, null, null);
            return tree;
        } else {
            int cmp = key.compareTo(tree.key);
            if (cmp < 0) {
                tree.left = insert(tree.left, key);
                if (height(tree.left) - height(tree.right) > 1) {
                    if (key.compareTo(tree.left.key) < 0) {
                        //type LL
                        tree = leftLeftRotation(tree);
                    } else if (key.compareTo(tree.left.key) > 0) {
                        //type LR
                        tree = leftRightRotation(tree);
                    }
                }
            } else if (cmp > 0) {
                tree.right = insert(tree.right, key);
                if (height(tree.right) - height(tree.left) > 1) {
                    if (key.compareTo(tree.right.key) > 0) {
                        //type RR
                        tree = rightRightRotation(tree);
                    } else if (key.compareTo(tree.right.key) < 0) {
                        //type LR
                        tree = rightLeftRotation(tree);
                    }
                }
            } else {
                System.out.println("不能插入相同值");
            }
            tree.height = max(height(tree.left), height(tree.right)) + 1;
            return tree;
        }
    }


    public void insert(T key) {
        mRoot = insert(mRoot, key);
    }

    /**
     * 迭代循环查找Key
     *
     * @param tree
     * @param key
     * @return
     */
    private AVLTreeNode<T> iterativeSearch(AVLTreeNode<T> tree, T key) {
        while (tree != null) {
            int cmp = key.compareTo(tree.key);
            if (cmp > 0)
                tree = tree.right;
            else if (cmp < 0)
                tree = tree.left;
            else
                return tree;
        }
        return tree;
    }

    public AVLTreeNode<T> iterativeSearch(T key) {
        return iterativeSearch(mRoot, key);
    }

    /**
     * 递归查找key
     *
     * @param tree
     * @param key
     * @return
     */
    private AVLTreeNode<T> search(AVLTreeNode<T> tree, T key) {
        int cmp = key.compareTo(tree.key);
        if (cmp > 0) {
            search(tree.right, key);
        } else if (cmp < 0)
            search(tree.left, key);
        return tree;
    }

    public AVLTreeNode<T> search(T key) {
        return search(mRoot, key);
    }

    private AVLTreeNode<T> minimum(AVLTreeNode<T> tree) {
        if (tree == null)
            return tree;
        while (tree.left != null) {
            tree = tree.left;
        }
        return tree;
    }

    private AVLTreeNode<T> maximum(AVLTreeNode<T> tree) {
        if (tree == null) {
            return tree;
        }
        while (tree.right != null) {
            tree = tree.right;
        }
        return tree;
    }

    public T minium() {
        return minimum(mRoot).key;
    }

    public T maximun() {
        return maximum(mRoot).key;
    }

    private AVLTreeNode<T> remove(AVLTreeNode<T> tree, AVLTreeNode<T> z) {
        if (tree == null || z == null)
            return null;
        int cmp = z.key.compareTo(tree.key);
        if (cmp > 0) {
            tree.right = remove(tree.right, z);
        } else if (cmp < 0) {
            tree.left = remove(tree.left, z);
            //删除左侧之后可能导致右侧不平衡
            if (height(tree.right)-height(tree.left)>1){

            }
        }
        return tree;
    }

}
