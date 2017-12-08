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

        public AVLTreeNode(T key, AVLTreeNode<T> left, AVLTreeNode<T> right, int height, AVLTreeNode<T> parent) {
            this.left = left;
            this.right = right;
            this.height = 0;
            this.key = key;
        }
    }

    /**
     * 获取二叉树的高度
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
        AVLTreeNode<T> k1 = k3.left;
        AVLTreeNode<T> k2 = k1.right;
        k1.left = k2.left;
        k2.left = k1;
        k3.left = k2.right;
        k2.right = k3;
        return k2;
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
}
