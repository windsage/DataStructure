package org.jeff.datastructure.binarytree;

public class BinaryNode<Any extends Comparable<Any>> {

    public Any data;
    public BinaryNode<Any> left;
    public BinaryNode<Any> right;
    public BinaryNode<Any> parent;

    public BinaryNode(Any data, BinaryNode<Any> left, BinaryNode<Any> right, BinaryNode<Any> parent) {
        this.data = data;
        this.left = left;
        this.right = right;
        this.parent = parent;
    }
}
