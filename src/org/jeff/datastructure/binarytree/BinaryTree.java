package org.jeff.datastructure.binarytree;

import java.util.Stack;

public class BinaryTree<Any extends Comparable<Any>> {

    private BinaryNode<Any> mRoot;

    public BinaryTree() {
        this.mRoot = null;
    }

    /**
     * 前序递归递归法
     *
     * @param root
     */
    private void preOrder1(BinaryNode<Any> root) {
        if (root == null)
            return;
        System.out.println(root.data);
        preOrder1(root.left);
        preOrder1(root.right);
    }


    /**
     * 前序遍历迭代法
     * 先入栈根节点，输出根节点val值，再先后入栈其右节点、左结点；
     * 2，出栈左节点，输出其val值，再入栈该左节点的右节点、左节点；直到遍历完该左节点所在子树。
     * 3，再出栈右节点，输出其val值，再入栈该右节点的右节点、左节点；直到遍历完该右节点所在子树。
     *
     * @param root
     */
    private void preOrder2(BinaryNode<Any> root) {
        if (root == null)
            return;
        Stack<BinaryNode<Any>> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            BinaryNode<Any> cur = stack.pop();
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
    private void inOrder1(BinaryNode<Any> root) {
        if (root == null)
            return;
        inOrder1(root.left);
        System.out.println(root.data);
        inOrder1(root.right);
    }

    /**
     * 中序遍历迭代法
     * 1、首先从根节点出发一路向左，入栈所有的左节点；
     * 2、出栈一个节点，输出该节点val值，查询该节点是否存在右节点，
     * 若存在则从该右节点出发一路向左入栈该右节点所在子树所有的左节点；
     * 3、若不存在右节点，则出栈下一个节点，输出节点val值，同步骤2操作；
     * 4、直到节点为null，且栈为空。
     *
     * @param root
     */
    private void inOrder2(BinaryNode<Any> root) {
        if (root == null)
            return;
        Stack<BinaryNode<Any>> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root.left);
                root = root.left;
            }
            if (!stack.isEmpty()) {
                BinaryNode<Any> node = stack.pop();
                System.out.println(node.data);
                root = root.right;
            }
        }
    }

    /**
     * 后序遍历递归法
     * 是用两个栈压入的，输出的left先放入s，再pop出放入output，就等于两个栈模拟了一个队列
     *
     * @param root
     */
    private void postOrder1(BinaryNode<Any> root) {
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
    private void postOrder2(BinaryNode<Any> root) {
        if (root == null)
            return;
        Stack<BinaryNode<Any>> s = new Stack<>();
        Stack<BinaryNode<Any>> output = new Stack<>();
        s.push(root);
        while (!s.isEmpty()) {
            BinaryNode cur = s.pop();
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
    private BinaryNode<Any> search1(BinaryNode<Any> root, Any key) {
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
    private BinaryNode<Any> search2(BinaryNode<Any> root, Any key) {
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
    private BinaryNode<Any> findMax(BinaryNode<Any> root) {
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
    private BinaryNode<Any> findMin(BinaryNode<Any> root) {
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
    private BinaryNode<Any> findPredecessor(BinaryNode<Any> root) {
        if (root.left != null)
            return findMax(root.left);
        BinaryNode<Any> node = root.parent;
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
    private void insertNode(BinaryNode<Any> root, Any key) {
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
