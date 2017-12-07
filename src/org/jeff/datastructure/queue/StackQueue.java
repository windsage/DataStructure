package org.jeff.datastructure.queue;

import java.util.Stack;

/**
 * 用两个栈模拟队列
 *
 * @param <T>
 */
public class StackQueue<T> {

    /**
     * 实现思路
     * in是入栈的，out是出栈的。
     * 入队列，直接压到in是就行了
     * 出队列，先把in中的元素全部出栈压入到out中，弹出out中的栈顶元素；再把out的所有元素全部压回in中
     */
    private Stack<T> mIn = null;
    private Stack<T> mOut = null;

    private int mCount;

    public int getSize() {
        return mCount;
    }

    public boolean isEmpty() {
        return mCount == 0;
    }

    public StackQueue() {
        mIn = new Stack<>();
        mOut = new Stack<>();
        mCount = 0;
    }

    public void add(T t) {
        while (!mOut.isEmpty()) {
            mIn.push(mOut.pop());
        }
        mIn.push(t);
        mCount++;
    }

    public T remove() {
        while (!mIn.isEmpty()) {
            T temp = mIn.pop();
            mOut.push(temp);
        }
        T result = mOut.pop();
        mCount--;
        return result;
    }
}
