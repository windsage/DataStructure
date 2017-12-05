package org.jeff.datastructure.binarytree;

import java.util.Stack;

public class BSTree<Any extends Comparable<Any>> {

    private BSTNode<Any> mRoot;

    public BSTree() {
        this.mRoot = null;
    }

    /**
     * 前序递归递归法
     *
     * @param root
     */
    private void preOrder1(BSTNode<Any> root) {
        if (root == null)
            return;
        System.out.println(root.data);
        preOrder1(root.left);
        preOrder1(root.right);
    }


    /**
     * 前序遍历迭代法
     *
     * @param root
     */
    private void preOrder2(BSTNode<Any> root) {
        if (root == null)
            return;
        Stack<BSTNode<Any>> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            BSTNode<Any> cur = stack.pop();
            System.out.println(cur.data);
            if (cur.right != null)
                stack.push(cur.right);
            if (cur.left != null)
                stack.push(cur.left);
        }
    }

    /**
     * 中序遍历递归法
     *
     * @param root
     */
    private void inOrder1(BSTNode<Any> root) {
        if (root == null)
            return;
        inOrder1(root.left);
        System.out.println(root.data);
        inOrder1(root.right);
    }

    /**
     * 中序遍历迭代法
     *
     * @param root
     */
    private void inOrder2(BSTNode<Any> root) {
        if (root == null)
            return;
        Stack<BSTNode<Any>> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root.left);
                root = root.left;
            }
            if (!stack.isEmpty()) {
                BSTNode<Any> node = stack.pop();
                System.out.println(node.data);
                root = root.right;
            }
        }
    }

    /**
     * 后序遍历递归法
     *
     * @param root
     */
    private void postOrder1(BSTNode<Any> root) {
        if (root == null) {
            return;
        }
        postOrder1(root.left);
        postOrder1(root.right);
        System.out.println(root.data);
    }

    /**
     * 后序遍历迭代法
     *
     * @param root
     */
    private void postOrder2(BSTNode<Any> root) {
        if (root == null)
            return;
        Stack<BSTNode<Any>> s = new Stack<>();
        Stack<BSTNode<Any>> output = new Stack<>();
        s.push(root);
        while (!s.isEmpty()) {
            BSTNode cur = s.pop();
            output.push(cur);
            if (cur.left != null)
                s.push(cur.left);
            if (cur.right != null)
                s.push(cur.right);
        }
        while (!output.isEmpty()) {
            System.out.println(output.pop().data);
        }
    }

    /**
     * 查找二叉树递归法
     *
     * @param root
     * @param key
     * @return
     */
    private BSTNode<Any> search1(BSTNode<Any> root, Any key) {
        if (root == null)
            return root;
        int cmp = key.compareTo(root.data);
        if (cmp < 0) {
            return search1(root.left, key);
        } else if (cmp > 0) {
            return search1(root.right, key);
        } else {
            return root;
        }
    }

    /**
     * 查找二叉树循环法
     *
     * @param root
     * @param key
     * @return
     */
    private BSTNode<Any> search2(BSTNode<Any> root, Any key) {
        while (root != null) {
            int cmp = key.compareTo(root.data);
            if (cmp < 0)
                root = root.left;
            else if (cmp > 0)
                root = root.right;
            else
                return root;
        }
        return root;
    }


    /**
     * 查找最大的节点
     *
     * @param root
     * @return
     */
    private BSTNode<Any> findMax(BSTNode<Any> root) {
        if (root == null)
            return root;
        while (root.right != null)
            root = root.right;
        return root;
    }

    /**
     * 查找最小的节点
     *
     * @param root
     * @return
     */
    private BSTNode<Any> findMin(BSTNode<Any> root) {
        if (root == null)
            return root;
        while (root.left != null)
            root = root.left;
        return root;
    }

    /**
     * 查找前驱
     * 节点的前驱："二叉树中数据值小于该结点"的"最大结点"。
     *
     * @param root
     * @return
     */
    private BSTNode<Any> findPredecessor(BSTNode<Any> root) {
        if (root.left != null)
            return findMax(root.left);
        BSTNode<Any> node = root.parent;
        while (node != null && root == node.parent) {
            root = node;
            node = node.parent;
        }
        return node;
    }


    /**
     * 二叉树添加节点
     *
     * @param root
     * @param key
     * @return
     */
    private void insertNode(BSTNode<Any> root, Any key) {
        if (root == null)
            return;
        int cmp = key.compareTo(root.data);
        if (cmp < 0) {
            if (root.left != null)
                insertNode(root.left, key);
        } else if (cmp > 0) {
            if (root.right != null)
                insertNode(root.right, key);
        }

    }
}
