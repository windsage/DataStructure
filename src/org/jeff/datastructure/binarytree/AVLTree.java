package org.jeff.datastructure.binarytree;

public class AVLTree<T extends Comparable<T>> {

    /**
     * 二叉平衡树结点
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
}
