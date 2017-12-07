package org.jeff.datastructure.stack;

import java.util.LinkedList;

/**
 * 用两个队列模拟栈
 *
 * @param <T>
 */
public class QueueStack<T> {
    /**
     * Java中Queue是一个接口，用LinkList实现队列
     */

    private LinkedList<T> mQ1 = null;
    private LinkedList<T> mQ2 = null;

    private int mCout;

    public QueueStack() {
        mQ1 = new LinkedList<>();
        mQ2 = new LinkedList<>();
        mCout = 0;
    }

    public int getSize() {
        return mCout;
    }

    public boolean isEmpty() {
        return mCout == 0;
    }

    public void push(T t) {
        if (!mQ2.isEmpty()) {
            mQ1.addLast(mQ2.removeFirst());
        }
        mQ1.addLast(t);
    }

    public T pop() {
        if (!mQ1.isEmpty()) {
            while (mQ1.size() > 1) {
                mQ2.addLast(mQ1.removeFirst());
            }
        }
        T t = mQ1.removeFirst();
        return t;
    }
}
