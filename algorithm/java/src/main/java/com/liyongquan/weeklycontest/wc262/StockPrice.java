package com.liyongquan.weeklycontest.wc262;

import java.util.*;

/**
 * @author liyongquan
 * @date 2021/10/10
 */
public class StockPrice {
    private TreeMap<Integer, Integer> m1;
    private TreeMap<Integer, Set<Integer>> m2;

    public StockPrice() {
        m1 = new TreeMap();
        m2 = new TreeMap();
    }

    public void update(int timestamp, int price) {
        if (!m1.containsKey(timestamp)) {
            m1.put(timestamp, price);
        } else {
            Integer old = m1.get(timestamp);
            m1.put(timestamp, price);
            m2.get(old).remove(timestamp);
            if (m2.get(old).size() == 0) {
                m2.remove(old);
            }
        }
        if (!m2.containsKey(price)) {
            m2.put(price, new HashSet<>());
        }
        m2.get(price).add(timestamp);
    }

    public int current() {
        Integer last = m1.lastKey();
        return m1.get(last);
    }

    public int maximum() {
        return m2.lastKey();
    }

    public int minimum() {
        return m2.firstKey();
    }
}
