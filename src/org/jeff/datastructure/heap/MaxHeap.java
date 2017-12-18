package org.jeff.datastructure.heap;

import java.util.ArrayList;
import java.util.List;

public class MaxHeap<T extends Comparable<T>> {

    private List<T> mHeap;

    public MaxHeap() {
        this.mHeap = new ArrayList<>();
    }
}
