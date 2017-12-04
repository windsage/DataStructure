package org.jeff.datastructure;

/**
 * 链表的增删改查
 *
 * @param <T>
 */
public class ArrayList<T> {


    private int theSize;
    private Node<T> head;

    public int getSize() {
        return this.theSize;
    }

    public void addNode(Node<T> node) {
        //判断头节点是否为空
        if (head == null) {
            head = node;
        } else {
            Node<T> temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = node;
        }
    }

    public void addNodeByIndex(Node<T> node, int index) {
        if (index < 1 && index > getSize()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        int length = 0;
        Node<T> temp = head;
        while (temp.next != null && length < index) {
            temp = temp.next;
            length++;
        }
        node.next = temp.next;
        temp.next = node;
    }
}
