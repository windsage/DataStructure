package org.jeff.datastructure.binarytree;

public class Huffman {

    private static class HuffmanNode implements Comparable, Cloneable {

        protected int key;              // 权值
        protected HuffmanNode left;     // 左孩子
        protected HuffmanNode right;    // 右孩子
        protected HuffmanNode parent;   // 父结点

        @Override
        public int compareTo(Object o) {
            return 0;
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            Object object = null;
            object = super.clone();
            return object;
        }
    }
}
