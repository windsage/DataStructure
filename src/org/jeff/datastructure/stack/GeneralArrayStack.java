package org.jeff.datastructure.stack;

import java.lang.reflect.Array;

/**
 * 数组模拟栈
 * @param <T>
 */
public class GeneralArrayStack<T> {

    private static final int DEFAULT_SIZE = 10;
    private T[] mArray;
    private int count;

    public GeneralArrayStack(Class<T> type, int size) {
        mArray = (T[]) Array.newInstance(type, size);
        count = 0;
    }

    public GeneralArrayStack(Class<T> type) {
        this(type, DEFAULT_SIZE);
    }

    /**
     * 压栈
     *
     * @param value
     */
    public void push(T value) {
        mArray[count] = value;
        count++;
    }

    /**
     * 出栈
     *
     * @return
     */
    public T pop() {
        T t = mArray[count - 1];
        count--;
        return t;
    }

    public boolean isEmpty() {
        return count == 0;
    }
}
