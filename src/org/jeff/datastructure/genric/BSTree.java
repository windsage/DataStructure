package org.jeff.datastructure.genric;

import java.util.Stack;

public class BSTree<Any extends Comparable<Any>> {

    public void preOrder(BSTNode<Any> root) {
        if (root == null)
            return;
        System.out.println(root.data);
        if (root.leftChild != null)
            preOrder(root.leftChild);
        if (root.rightChild != null)
            preOrder(root.rightChild);
    }


    private void preOrder2(BSTNode<Any> root) {
        if (root == null)
            return;
        Stack<BSTNode<Any>> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            BSTNode<Any> cur = stack.pop();
            System.out.println(cur.data);
            if (cur.rightChild != null)
                stack.push(cur.rightChild);
            if (cur.leftChild != null)
                stack.push(cur.leftChild);
        }
    }

    /**
     * left root right
     *
     * @param root
     */
    private void inOrder(BSTNode<Any> root) {
        if (root == null)
            return;
        Stack<BSTNode<Any>> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root.leftChild);
                root = root.leftChild;
            }
            if (!stack.isEmpty()) {
                BSTNode<Any> node = stack.pop();
                System.out.println(node.data);
                root = root.rightChild;
            }
        }
    }
}
