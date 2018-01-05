package org.jeff.datastructure.binarytree;

public class RBTree<T extends Comparable<T>> {

    private static final boolean RED = false;
    private static final boolean BLACK = true;

    private static class RBTNode<T extends Comparable<T>> {
        T key;
        boolean color;
        RBTNode<T> left;
        RBTNode<T> right;
        RBTNode<T> parent;

        public RBTNode(T key, boolean color, RBTNode<T> left, RBTNode<T> right, RBTNode<T> parent) {
            this.key = key;
            this.color = color;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }

        public T getKey() {
            return key;
        }
    }

    private RBTNode<T> mRoot;

    public RBTree() {
        mRoot = null;
    }

    private RBTNode<T> parentOf(RBTNode<T> node) {
        return node.parent;
    }

    private boolean isRed(RBTNode<T> node) {
        return node.color;
    }

    private RBTNode<T> grandParentOf(RBTNode<T> node) {
        if (node.parent != null) {
            return node.parent.parent;
        } else {
            return null;
        }
    }

    private boolean colorOf(RBTNode<T> node) {
        return node != null ? node.color : BLACK;
    }

    private boolean isBlack(RBTNode<T> node) {
        return !isRed(node);
    }

    private void setBlack(RBTNode<T> node) {
        if (node != null)
            node.color = BLACK;
    }

    private void setRed(RBTNode<T> node) {
        if (node != null)
            node.color = RED;
    }
}
