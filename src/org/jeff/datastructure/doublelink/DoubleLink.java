package org.jeff.datastructure.doublelink;

/**
 * 这是一个带头结点的双向链表
 * @param <T>
 */
public class DoubleLink<T> {
    private DoubleNode<T> head;
    private int size;

    /**
     * 双向链表的结点
     *
     * @param <T>
     */
    private class DoubleNode<T> {
        public T data;
        public DoubleNode<T> previous;
        public DoubleNode<T> next;

        public DoubleNode(T data, DoubleNode<T> previous, DoubleNode<T> next) {
            this.data = data;
            this.previous = previous;
            this.next = next;
        }
    }

    /**
     * 构造函数
     */
    public DoubleLink() {
        head = new DoubleNode<>(null, null, null);
        head.previous = head.next = head;
        this.size = 0;
    }

    /**
     * 获取长度
     *
     * @return
     */
    public int getSize() {
        return size;
    }

    /**
     * 查找指定的结点
     *
     * @param index
     * @return
     */
    private DoubleNode<T> getNode(int index) {
        if (index < 0 || index > size - 1) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (index < size / 2) {
            //正向查找
            DoubleNode<T> node = head;
            for (int current = 0; current <= index; current++) {
                node = node.next;
            }
            return node;
        } else {
            //反向查找
            DoubleNode<T> node = head.previous;
            for (int current = size - 1; current >= index; current--) {
                node = node.previous;
            }
            return node;
        }
    }

    /**
     * 获取指定位置结点的值
     *
     * @param index
     * @return
     */
    private T get(int index) {
        return getNode(index).data;
    }

    /**
     * 最后一个结点的值
     *
     * @return
     */
    private T getLast() {
        return getNode(size - 1).data;
    }

    /**
     * 在指定位置插入结点
     *
     * @param index
     * @param value
     */
    private void insert(int index, T value) {
        if (index == 0) {
            DoubleNode<T> node = new DoubleNode<>(value, head, head.next);
            head.next.previous = node;
            head.next = node;
            size++;
        } else {
            DoubleNode<T> node = getNode(index);
            DoubleNode<T> insert = new DoubleNode<T>(value, node.previous, node);
            node.previous.next = insert;
            node.previous = insert;
            size++;
        }
    }

    /**
     * 表尾增加结点
     *
     * @param value
     */
    private void appendLast(T value) {
        DoubleNode<T> insert = new DoubleNode<T>(value, head.previous, head);
        head.previous = insert;
        head.previous.next = insert;
        size++;
    }

    /**
     * 移除指定位置的结点
     *
     * @param index
     */
    private void remove(int index) {
        DoubleNode<T> temp = getNode(index);
        temp.previous.next = temp.next;
        temp.next.previous = temp.previous;
        size--;
    }

    /**
     * 删除指定位置的结点
     */
    private void deleteLast() {
        remove(size - 1);
    }
}
