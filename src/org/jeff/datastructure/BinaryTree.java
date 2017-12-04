package org.jeff.datastructure;

import java.util.Stack;

public class BinaryTree {

    /**
     * 前序遍历递归法
     *
     * @param root
     */
    public void preOrderTraversalRec(TreeNode root) {
        if (root == null)
            return;
        System.out.println("data--->" + root.data);
        preOrderTraversalRec(root.left);
        preOrderTraversalRec(root.right);

    }


    /**
     * 1，先入栈根节点，输出根节点val值，再先后入栈其右节点、左结点；
     * 2，出栈左节点，输出其val值，再入栈该左节点的右节点、左节点；直到遍历完该左节点所在子树。
     * 3，再出栈右节点，输出其val值，再入栈该右节点的右节点、左节点；直到遍历完该右节点所在子树。
     *
     * @param root
     */
    public void preOrderTraversal(TreeNode root) {
        if (root == null)
            return;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode current = stack.pop();
            System.out.println("data--->" + current.data);
            if (current.right != null) {
                stack.add(current.right);
            }
            if (current.left != null) {
                stack.add(current.left);
            }
        }
    }


    /**
     * 中序遍历，左根右
     *
     * @param root
     */
    public void inOrderTraversalRec(TreeNode root) {
        if (root == null)
            return;
        inOrderTraversalRec(root.left);
        System.out.println("data--->" + root.data);
        inOrderTraversalRec(root.right);
    }

    /**
     * 1、首先从根节点出发一路向左，入栈所有的左节点；
     * 2、出栈一个节点，输出该节点val值，查询该节点是否存在右节点，
     * 若存在则从该右节点出发一路向左入栈该右节点所在子树所有的左节点；
     * 3、若不存在右节点，则出栈下一个节点，输出节点val值，同步骤2操作；
     * 4、直到节点为null，且栈为空。
     *
     * @param root
     */
    public void inOrderTreversal(TreeNode root) {
        if (root == null)
            return;
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            if (!stack.isEmpty()) {
                TreeNode node = stack.pop();
                System.out.println("data--->" + node.data);
                root = node.right;
            }
        }
    }


    public void postOrderTraversalRec(TreeNode root) {
        if (root == null)
            return;
        postOrderTraversalRec(root.left);
        postOrderTraversalRec(root.right);
        System.out.println("data--->" + root.data);
    }


    /**
     * 后序遍历，是用两个栈压入的，输出的left先放入s，再pop出放入output，就等于两个栈模拟了一个队列
     *
     * @param root
     */
    public void postOrderTraversal(TreeNode root) {
        if (root == null)
            return;
        Stack<TreeNode> s = new Stack<>();
        Stack<TreeNode> output = new Stack<>();
        s.push(root);
        while (!s.isEmpty()) {
            TreeNode cur = s.pop();
            output.push(cur);
            if (cur.left != null)
                s.push(cur.left);
            if (cur.right != null)
                s.push(cur.right);
        }
        while (!output.isEmpty())
            System.out.println("data--->" + output.pop().data);
    }


    public static void main(String[] args) {
        TreeNode a = new TreeNode("a", null, null);
        TreeNode b = new TreeNode("b", null, null);
        TreeNode c = new TreeNode("c", null, null);
        TreeNode d = new TreeNode("d", null, null);
        TreeNode e = new TreeNode("e", null, null);
        TreeNode f = new TreeNode("f", null, null);
        TreeNode g = new TreeNode("g", null, null);
        a.left = b;
        a.right = c;
        b.left = d;
        b.right = f;
        d.right = e;
        f.left = g;
        BinaryTree tree = new BinaryTree();
        tree.postOrderTraversal(a);
    }
}
