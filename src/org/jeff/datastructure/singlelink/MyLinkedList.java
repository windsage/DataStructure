package org.jeff.datastructure.singlelink;

public class MyLinkedList {

    private LinkedNode head;
    private LinkedNode tail;

    public MyLinkedList() {
        size = 0;
        head = new LinkedNode(null, null, null);
        tail = new LinkedNode(null, head, null);
    }

    private int size;

    public void setSize(int size) {
        this.size = size;
    }

    public void addLinkedNode(LinkedNode node) {
       if (head == null)
            head = node;
    }
}
