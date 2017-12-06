package org.jeff.datastructure.singlelink;

/**
 * 这是一个不带头结点的单项链表
 *
 * @param <T>
 */
public class SingleLinkWithoutHead<T> {

    private class Node<T> {
        public Node<T> next;
        public T data;

        public Node(T data, Node<T> next) {
            this.next = next;
            this.data = data;
        }
    }

    private Node<T> head;
    private int size;

    public SingleLinkWithoutHead() {
        this.head = null;
        this.size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return head == null;
    }


    private Node<T> getNode(int index) {
        if (index < 0 || index >= size)
            throw new ArrayIndexOutOfBoundsException();
        else if (index == 0) {
            return head;
        } else {
            Node<T> temp = head;
            int current = 0;
            while (current < index) {
                temp = temp.next;
                current++;
            }
            return temp.next;
        }
    }

    private void insert(T value) {
        Node<T> node = new Node<T>(value, null);
        if (head == null)
            head = node;
        else {
            Node<T> end = getNode(size - 1);
            end.next = node;
        }
        size++;
    }
}
