package org.jeff.datastructure;

public class LinkedNode {

    public Integer data;
    public LinkedNode pre;
    public LinkedNode next;

    public LinkedNode(Integer data, LinkedNode pre, LinkedNode next) {
        this.data = data;
        this.pre = pre;
        this.next = next;
    }
}
