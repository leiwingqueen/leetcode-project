package com.liyongquan.design;

import java.util.Iterator;

public class PeekingIterator implements Iterator<Integer> {
    private Iterator<Integer> iterator;
    private Integer cur;

    public PeekingIterator(Iterator<Integer> iterator) {
        this.iterator = iterator;
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        if (cur != null) {
            return cur;
        }
        if (!iterator.hasNext()) {
            return null;
        }
        Integer next = iterator.next();
        cur = next;
        return next;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        if (cur != null) {
            Integer res = cur.intValue();
            cur = null;
            return res;
        }
        return iterator.next();
    }

    @Override
    public boolean hasNext() {
        return cur != null || iterator.hasNext();
    }
}
