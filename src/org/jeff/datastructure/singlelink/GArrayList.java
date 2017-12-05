package org.jeff.datastructure.singlelink;

/**
 * use generic class over ride arraylist
 *
 * @param <Any>
 * @author Jeffery
 */
public class GArrayList<Any extends Comparable<Any>> {

    private GNode<Any> head;
    private int size;

    public int getSize() {
        return size;
    }

    public void addNode(GNode<Any> node) {
        if (head == null)
            head = node;
        while (head.next != null)
            head = head.next;
        head.next = node;
    }


}
