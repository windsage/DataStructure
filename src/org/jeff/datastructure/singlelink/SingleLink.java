package org.jeff.datastructure.singlelink;

public class SingleLink<T> {
    private SingleNode<T> head;

    private int size;

    private class SingleNode<T> {
        public SingleNode<T> next;
        public T data;

        public SingleNode(T data, SingleNode<T> next) {
            this.next = next;
            this.data = data;
        }
    }

    public SingleLink() {
        this.head = null;
        this.size = 0;
    }

    /**
     * 添加一个节点
     *
     * @param node
     */
    private void insert(SingleNode<T> node) {
        if (head == null)
            head = node;
        else {
            SingleNode<T> temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = node;
        }
        size++;
    }

    /**
     * 指定位置添加一个节点
     *
     * @param node
     * @param index
     */
    private void insert(SingleNode<T> node, int index) {
        if (index > size + 1 || size < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (head == null && index == 0)
            head = node;
        else {
            int currentIndex = 0;
            SingleNode<T> temp = head;
            while (currentIndex < index) {
                temp = temp.next;
                currentIndex++;
            }
            node.next = temp.next;
            temp.next = node;
        }
    }

    /**
     * 移除一个节点(循环)
     *
     * @param node
     */
    private void remove(SingleNode<T> node) {
        if (head == null)
            return;
        SingleNode<T> temp = head;
        while (temp.next != node) {
            temp = temp.next;
        }
        temp.next = node.next;
        size--;
    }

    /**
     * 删除节点2
     *
     * @param node
     */
    private void remove2(SingleNode<T> node) {
        if (head == null)
            return;
        SingleNode<T> temp = node.next;
        node.data = temp.data;
        node.next = temp.next;
    }

//    private void
}
