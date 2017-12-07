package org.jeff.datastructure.queue;

import java.lang.reflect.Array;
import java.util.NoSuchElementException;

/**
 * 用数组模拟一个队列
 *
 * @param <T>
 */
public class ArrayQueue<T> {

    private static final int DEFAULT_SIZE = 10;
    private T[] mArray;
    private int mCount;

    public ArrayQueue(Class<T> type, int size) {
        mArray = (T[]) Array.newInstance(type, size);
        mCount = 0;
    }


    public ArrayQueue(Class<T> type) {
        this(type, DEFAULT_SIZE);
    }

    /**
     * add        增加一个元索                     如果队列已满，则抛出一个IIIegaISlabEepeplian异常
     remove   移除并返回队列头部的元素    如果队列为空，则抛出一个NoSuchElementException异常
     element  返回队列头部的元素             如果队列为空，则抛出一个NoSuchElementException异常
     offer       添加一个元素并返回true       如果队列已满，则返回false
     poll         移除并返问队列头部的元素    如果队列为空，则返回null
     peek       返回队列头部的元素             如果队列为空，则返回null
     put         添加一个元素                      如果队列满，则阻塞
     take        移除并返回队列头部的元素     如果队列为空，则阻塞
     */
    /**
     * 判断队列是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return mCount == 0;
    }

    /**
     * 入队
     *
     * @param t
     */
    public void add(T t) {
        if (mCount == DEFAULT_SIZE)
            throw new IllegalStateException();
        mArray[mCount++] = t;
    }

    /**
     * 出队
     *
     * @return
     */
    public T remove() {
        if (mCount == 0)
            throw new NoSuchElementException();
        T t = mArray[0];
        for (int i = 1; i < mCount; i++) {
            mArray[i - 1] = mArray[i];
        }
        mCount--;
        return t;
    }

    /**
     * 查看队头元素
     *
     * @return
     */
    public T peek() {
        return mArray[0];
    }
}
