package org.jeff.datastructure.singlelink;

import java.util.HashSet;
import java.util.Stack;

/**
 * 带头结点的单链表
 *
 * @param <T>
 */
public class SingleLinkWithHead<T> {
    private SingleNode<T> head;

    private int size;

    private class SingleNode<T> {
        public SingleNode<T> next;
        public T data;

        public SingleNode(T data, SingleNode<T> next) {
            this.next = next;
            this.data = data;
        }
    }

    /**
     * 构造函数
     * 这里是关键，区别了有头结点和无头结点的链表
     * 这里的head≠null，但是head的value是null,head的next结点是null
     */
    public SingleLinkWithHead() {
        this.head = new SingleNode<T>(null, null);
        this.size = 0;
    }

    /**
     * 判断链表是否为空
     *
     * @return
     */
    private boolean isEmpty() {
        return head.next == null;
    }

    /**
     * 添加一个结点
     *
     * @param node
     */
    private void insert(SingleNode<T> node) {
        SingleNode<T> temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = node;
        size++;
    }

    /**
     * 指定位置添加一个结点
     *
     * @param node
     * @param index
     */
    private void insert(SingleNode<T> node, int index) {
        if (index > size + 1 || size < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (head == null && index == 0)
            head.next = node;
        else {
            int currentIndex = 0;
            SingleNode<T> temp = head;
            while (currentIndex < index) {
                temp = temp.next;
                currentIndex++;
            }
            node.next = temp.next;
            temp.next = node;
            size++;
        }
    }

    /**
     * 移除一个结点(循环)
     *
     * @param node
     */
    private void remove(SingleNode<T> node) {
        if (head == node) {
            head = null;
            size = 0;
        } else {
            SingleNode<T> temp = head;
            while (temp.next != node) {
                temp = temp.next;
            }
            temp.next = node.next;
            size--;
        }
    }

    /**
     * 删除指定索引的结点
     *
     * @param index
     */
    private void removeByIndex(int index) {
        if (index < 1 || index > size - 1) {
            throw new ArrayIndexOutOfBoundsException();
        }
        int current = 0;
        SingleNode<T> temp = head;
        while (current < index) {
            temp = temp.next;
            current++;
        }
        temp.next = temp.next.next;
    }

    /**
     * 删除结点2
     * 此处需要确认
     *
     * @param node
     */
    private void remove2(SingleNode<T> node) {
        if (head == node) {
            head = null;
            size = 0;
        } else {
            SingleNode<T> temp = node.next;
            node.data = temp.data;
            node.next = temp.next;
            size--;
        }
    }

    /**
     * 用栈的方式删除指定索引的结点
     *
     * @param index
     */
    private void removeByStack(int index) {
        Stack<SingleNode<T>> stack = new Stack<>();
        stack.push(head);
        int current = 0;
        while (head != null) {
            head = head.next;
            current++;
            if (current != index)
                stack.push(head);
        }
        while (!stack.isEmpty()) {
            stack.peek().next = head;
            head = stack.pop();
        }
    }

    /**
     * 删除重复数据
     */
    private void removeDuplication() {
        HashSet<T> set = new HashSet<>();
        SingleNode<T> previous = head;
        SingleNode<T> current = head.next;
        set.add(head.data);
        while (previous != null) {
            if (!set.contains(previous.data)) {
                set.add(previous.data);
                previous = current;
                current = current.next;
            } else {
                previous.next = current.next;
                current = current.next;
            }
        }
    }


    /**
     * 移除倒数第K个结点
     *
     * @param k
     */
    private void removeLastKthNode(int k) {
        if (k < 0 || k > size)
            throw new ArrayIndexOutOfBoundsException();
        SingleNode<T> temp = head;
        for (int i = 0; i < k; i++) {
            temp = temp.next;
        }
        SingleNode<T> previous = head;
        while (temp.next != null) {
            temp = temp.next;
            previous = previous.next;
        }
        previous.next = previous.next.next;
    }

    /**
     * 反转链表递归
     */
    private SingleNode<T> reverse(SingleNode<T> node) {
        if (node == null || node.next == null)
            return node;
        SingleNode<T> previous = reverse(node.next);
        node.next.next = node;
        node.next = null;
        return previous;
    }

    /**
     * 链表反转非递归
     *
     * @param head
     * @return
     */
    private SingleNode<T> reverse2(SingleNode<T> head) {
        SingleNode<T> previous = null;
        while (head != null) {
            SingleNode<T> temp = head.next;
            head.next = previous;
            previous = head;
            head = temp;
        }
        return previous;
    }

    private void reverse3(SingleNode<T> head) {
        Stack<SingleNode<T>> stack = new Stack<>();
        while (head != null) {
            stack.push(head);
            head = head.next;
        }
        SingleNode<T> first = null;
        while (!stack.isEmpty()) {
            SingleNode<T> temp = stack.pop();
            first.next = temp;
            first = temp;
        }
    }

    /**
     * 查找一个未知长度的链表的中间结点
     *
     * @return
     */
    private SingleNode<T> findMiddle() {
        SingleNode<T> p = head;
        SingleNode<T> q = head;
        while (q != null && q.next != null && q.next.next != null) {
            p = p.next;
            q = q.next.next;
        }
        return p;
    }


    /**
     * 判断两个链表是否相交
     * 参考http://blog.csdn.net/zengzhen_csdn/article/details/49761869
     * 此方法时间复杂度为O(m+n)
     *
     * @param h1
     * @param h2
     * @return
     */
    private boolean isIntersect(SingleNode<T> h1, SingleNode<T> h2) {
        if (h1 == null || h2 == null)
            return false;
        while (h1 != null) {
            h1 = h1.next;
        }
        while (h2 != null) {
            h2 = h2.next;
        }
        return h1 == h2;
    }

    

}
