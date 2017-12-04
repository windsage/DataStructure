package org.jeff.datastructure;

/**
 * 构造一个节点，Node中有一个数据data，以及下一个Node
 *
 * @param <T>
 */
public class Node<T> {
    public T data;
    public Node<T> next;

    public Node(T data) {
        this.data = data;
    }
}
