package org.jeff.datastructure.binarytree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BinaryTree<Any extends Comparable<Any>> {
    private class BinaryNode<Any extends Comparable<Any>> {

        public Any data;
        public BinaryNode<Any> left;
        public BinaryNode<Any> right;
        public BinaryNode<Any> parent;

        public BinaryNode(Any data, BinaryNode<Any> left, BinaryNode<Any> right, BinaryNode<Any> parent) {
            this.data = data;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }
    }

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
     * 先入栈根结点，输出根结点val值，再先后入栈其右结点、左结点；
     * 2，出栈左结点，输出其val值，再入栈该左结点的右结点、左结点；直到遍历完该左结点所在子树。
     * 3，再出栈右结点，输出其val值，再入栈该右结点的右结点、左结点；直到遍历完该右结点所在子树。
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
     * 1、首先从根结点出发一路向左，入栈所有的左结点；
     * 2、出栈一个结点，输出该结点val值，查询该结点是否存在右结点，
     * 若存在则从该右结点出发一路向左入栈该右结点所在子树所有的左结点；
     * 3、若不存在右结点，则出栈下一个结点，输出结点val值，同步骤2操作；
     * 4、直到结点为null，且栈为空。
     *
     * @param root
     */
    private void inOrder2(BinaryNode<Any> root) {
        if (root == null)
            return;
        Stack<BinaryNode<Any>> stack = new Stack<>();
        BinaryNode<Any> cur = root;
        while (!stack.isEmpty() || cur != null) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            if (!stack.isEmpty()) {
                cur = stack.pop();
                System.out.println(cur.data);
                cur = cur.right;
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
     * 查找最大的结点
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
     * 查找最小的结点
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
     * 结点的前驱："二叉树中数据值小于该结点"的"最大结点"。
     *
     * @param root
     * @return
     */
    private BinaryNode<Any> findPredecessor(BinaryNode<Any> root) {
        if (root.left != null)
            return findMax(root.left);
        else {
            // 如果x没有左孩子。则x有以下两种可能：
            // (01) x是"一个右孩子"，则"x的前驱结点"为 "它的父结点"。
            // (01) x是"一个左孩子"，则查找"x的最低的父结点，并且该父结点要具有右孩子"，找到的这个"最低的父结点"就是"x的前驱结点"。
            BinaryNode<Any> node = root.parent;
            while (node != null && root == node.parent) {
                root = node;
                node = node.parent;
            }
            return node;
        }
    }

    /**
     * 查找后继
     * 结点的后继：查找"二叉树中数据值大于该结点"的"最小结点"。
     *
     * @param x
     * @return
     */
    private BinaryNode<Any> findSuccessor(BinaryNode<Any> x) {
        if (x.right != null) {
            return findMin(x.right);
        } else {
            // 如果x没有右孩子。则x有以下两种可能：
            // (01) x是"一个左孩子"，则"x的后继结点"为 "它的父结点"。
            // (02) x是"一个右孩子"，则查找"x的最低的父结点，并且该父结点要具有左孩子"，找到的这个"最低的父结点"就是"x的后继结点"。
            BinaryNode<Any> y = x.parent;
            while ((y != null) && (x == y.right)) {
                x = y;
                y = y.parent;
            }

            return y;
        }
    }

    /**
     * 二叉树添加结点
     *
     * @param root
     * @param key
     * @return
     */
    private BinaryNode<Any> insertNode(BinaryNode<Any> root, Any key) {
        if (root == null)
            return new BinaryNode<>(key, null, null, null);
        int cmp = key.compareTo(root.data);
        if (cmp < 0) {
            root.left = insertNode(root.left, key);
        } else if (cmp > 0) {
            root.right = insertNode(root.right, key);
        }
        return root;
    }


    private void insert(BinaryTree<Any> tree, BinaryNode<Any> z) {
        BinaryNode<Any> x = tree.mRoot;
        BinaryNode<Any> y = null;
        int cmp;
        while (x != null) {
            y = x;
            cmp = z.data.compareTo(x.data);
            if (cmp < 0)
                x = x.left;
            else
                x = x.right;
        }
        z.parent = y;
        if (y == null)
            tree.mRoot = z;
        else {
            cmp = z.data.compareTo(y.data);
            if (cmp < 0)
                y.left = z;
            else
                y.right = z;
        }
    }


    /**
     * 查找x的后继结点
     *
     * @param x
     * @return
     */
    private BinaryNode<Any> successor(BinaryNode<Any> x) {
        if (x.right != null)
            return findMin(x.right);
        BinaryNode<Any> y = x.parent;
        while (y != null && x == y.parent) {
            x = y;
            y = y.parent;
        }
        return y;
    }


    /**
     * 找到x结点的前驱
     *
     * @param x
     * @return
     */
    private BinaryNode<Any> predecessor(BinaryNode<Any> x) {
        if (x.left != null)
            return findMax(x.left);
        BinaryNode<Any> y = x.parent;
        while (y != null && x == y.left) {
            x = y;
            y = y.parent;
        }
        return y;
    }

    /**
     * 二叉树删除结点
     *
     * @param bst
     * @param z
     * @return
     */
    private BinaryNode<Any> remove(BinaryTree<Any> bst, BinaryNode<Any> z) {
        BinaryNode<Any> x = null;
        BinaryNode<Any> y = null;
        if (z.left != null || z.right == null)
            y = z;
        else
            y = successor(z);
        if (y.left != null)
            x = y.left;
        else
            x = y.right;

        if (x != null)
            x.parent = y.parent;
        if (y.parent == null)
            bst.mRoot = x;
        else if (y == y.parent.left) {
            y.parent.left = x;
        } else
            y.parent.right = x;

        if (y != z)
            z.data = y.data;
        return y;
    }


    /**
     * 二叉树的广度搜索
     * @param root
     */
    private void BFS(BinaryNode<Any> root) {
        if (root == null)
            return;
        Queue<BinaryNode<Any>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            BinaryNode<Any> cur = queue.poll();
            System.out.print(cur.data + " ");
            if (cur.left != null)
                queue.offer(cur.left);
            if (cur.right != null)
                queue.offer(cur.right);
        }
    }

}
