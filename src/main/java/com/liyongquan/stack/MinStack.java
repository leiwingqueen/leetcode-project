package com.liyongquan.stack;

public class MinStack {
    /** initialize your data structure here. */
    public static final int INIT_SIZE=16;
    private int size=0;
    private int min=0;
    private int[] array=new int[INIT_SIZE];
    public MinStack() {
    }

    public void push(int x) {
        size++;
        array[size]=x;
    }

    public void pop() {
        if (size>0) {
            size--;
        }
    }

    public int top() {
        return array[size-1];
    }

    public int min() {
        return min;
    }
}
