package org.jeff.datastructure.genric;

public class BSTNode<AnyType extends Comparable<AnyType>> {

    public AnyType data;
    public BSTNode<AnyType> leftChild;
    public BSTNode<AnyType> rightChild;

    public BSTNode(AnyType data, BSTNode<AnyType> leftChild, BSTNode<AnyType> rightChild) {
        this.data = data;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

}
