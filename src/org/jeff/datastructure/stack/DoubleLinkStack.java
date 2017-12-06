package org.jeff.datastructure.stack;

/**
 * 用双向链表模拟一个栈
 *
 * @param <T>
 */
public class DoubleLinkStack<T> {
    private class Node<T> {
        T data;
        Node<T> pre;
        Node<T> next;

        public Node(T data, Node<T> pre, Node<T> next) {
            this.data = data;
            this.pre = pre;
            this.next = next;
        }
    }

    private Node<T> head;
    private int size;

    public int getSize() {
        return this.size;
    }

    public DoubleLinkStack() {
        this.head = new Node<>(null, null, null);
        head.next = head.pre = head;
    }

    public void push(T t) {
        if (getSize() == 0) {
            Node<T> newNode = new Node<>(t, head, head.next);
            head.next.pre = newNode;
            head.next = newNode;
            size++;
        } else {
            Node<T> newNode = new Node<>(t, head.pre, head);
            head.pre.next = newNode;
            head.pre = newNode;
            size++;
        }
    }

    public T pop() {
        Node<T> last = head.pre;
        last.pre.next = head;
        head.pre = last;
        size--;
        return last.data;
    }
}
