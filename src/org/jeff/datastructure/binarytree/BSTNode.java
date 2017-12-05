package org.jeff.datastructure.binarytree;

public class BSTNode<Any extends Comparable<Any>> {

    public Any data;
    public BSTNode<Any> left;
    public BSTNode<Any> right;
    public BSTNode<Any> parent;

    public BSTNode(Any data, BSTNode<Any> left, BSTNode<Any> right, BSTNode<Any> parent) {
        this.data = data;
        this.left = left;
        this.right = right;
        this.parent = parent;
    }
}
