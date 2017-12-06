package org.jeff.datastructure.singlelink;

import java.util.HashSet;
import java.util.Stack;

/**
 * 链表的增删改查
 *
 * @param <T>
 */
public class ArrayList {
    /**
     * 构造一个结点，Node中有一个数据data，以及下一个Node
     */
    private class Node {
        public int data;
        public Node next;

        public Node(int data) {
            this.data = data;
        }
    }


    private int theSize;
    private Node head;

    private int getSize() {
        return this.theSize;
    }

    public void addNode(Node node) {
        //判断头结点是否为空
        if (head == null) {
            head = node;
        } else {
            Node temp = head;
            while (temp != null) {
                temp = temp.next;
            }
            temp.next = node;
            theSize++;
        }
    }

    public void addNode(Node node, int index) {
        if (getSize() == 0) {
            if (index != 0) {
                throw new ArrayIndexOutOfBoundsException();
            } else {
                head = node;
                theSize++;
            }
        } else {
            int length = 1;//这里定义为length，是实际长度要比索引大1
            Node temp = head;
            while (length < index + 1) {
                temp = temp.next;
                length++;
            }
            node.next = temp.next;
            temp.next = node;
        }
    }


    public void deleteNode(int index) {
        if (index < 1 || index > getSize()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        int length = 1;
        Node temp = head;
        while (length != index + 1) {
            temp = temp.next;
            length++;
        }
        temp.next = temp.next.next;
        theSize--;
    }

    public void deleteNode(Node node) {
        //判断这个结点是否头结点
        if (head == node) {
            head = null;
            theSize = 0;
        } else {
            Node temp = head;
            while (temp.next != node) {
                temp = temp.next;
            }
            temp.next = node.next;
            theSize--;
        }
    }


    /**
     * 这是用一个栈存储了除了index外的结点，在用pop的方式弹出
     * 其中peek表示查看栈顶对象，并不弹出
     *
     * @param index
     */
    public void deletNodeByStack(int index) {
        Stack<Node> stack = new Stack<>();
        int length = 1;
        while (head != null) {
            if (length != index)
                stack.push(head);
            head = head.next;
            length++;
        }
        while (!stack.isEmpty()) {
            stack.peek().next = head;
            head = stack.pop();
        }
        theSize--;
    }

    /**
     * 此方法的关键在于把每个结点往前移动一个
     *
     * @param node
     */
    public void deleteNodeWithoutInterator(Node node) {
        Node temp = node.next;
        node.data = temp.data;
        node.next = temp.next;
        theSize--;
    }


    /**
     * 删除链表中重复的值
     * 思路不重复筛选用Set实现
     */
    public void deleteDuplication() {
        if (head == null)
            return;
        HashSet<Integer> set = new HashSet<>();
        set.add(head.data);
        Node pre = head;
        Node cur = head.next;
        while (cur != null) {
            if (set.contains(cur.data)) {
                pre.next = cur.next;
            } else {
                set.add(cur.data);
                pre = cur;
            }
            cur = cur.next;
        }
    }


    /**
     * 移除倒数第K个结点
     *
     * @param k
     */
    public void removeLastKthNdoe(int k) {
        if (k > getSize() || k < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        Node p = head;
        Node q = head;
        for (int i = 0; i < k; i++) {
            p = p.next;
        }
        while (p.next != null) {
            p = p.next;
            q = q.next;
        }
        q.next = q.next.next;
    }


    public void deleteHead() {
        if (head == null)
            return;
        head = head.next;
    }
}
