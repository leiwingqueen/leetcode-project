package com.liyongquan.design;

import java.util.LinkedList;
import java.util.List;

/**
 * 不使用任何内建的哈希表库设计一个哈希映射
 * <p>
 * 具体地说，你的设计应该包含以下的功能
 * <p>
 * put(key, value)：向哈希映射中插入(键,值)的数值对。如果键对应的值已经存在，更新这个值。
 * get(key)：返回给定的键所对应的值，如果映射中不包含这个键，返回-1。
 * remove(key)：如果映射中存在这个键，删除这个数值对。
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/design-hashmap
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MyHashMap {
    public static final int INIT_SIZE = 16;
    private volatile int size = 0;
    private volatile int realSize = 0;
    private volatile List<KV<Integer, Integer>>[] map;

    /**
     * Initialize your data structure here.
     */
    public MyHashMap() {
        map = new List[INIT_SIZE];
        size = INIT_SIZE;
    }

    /**
     * value will always be non-negative.
     */
    public void put(int key, int value) {
        synchronized (MyHashMap.class) {
            if (realSize == size) {
                resize();
            }
            put(key, value, map, size);
            realSize++;
        }
    }

    private void resize() {
        int newsize = size * 2;
        List<KV<Integer, Integer>>[] newmap = new List[newsize];
        for (List<KV<Integer, Integer>> kvs : map) {
            if (kvs == null) {
                continue;
            }
            for (KV<Integer, Integer> kv : kvs) {
                put(kv.key, kv.value, newmap, newsize);
            }
        }
        this.map = newmap;
        this.size = newsize;
    }

    private void put(int key, int value, List<KV<Integer, Integer>>[] map, int size) {
        int index = key % size;
        if (map[index] == null) {
            map[index] = new LinkedList<>();
        }
        List<KV<Integer, Integer>> list = map[index];
        for (KV<Integer, Integer> item : list) {
            if (item.key == key) {
                item.value = value;
                return;
            }
        }
        KV<Integer, Integer> kv = new KV<>();
        kv.key = key;
        kv.value = value;
        map[index].add(kv);
    }

    /**
     * Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key
     */
    public int get(int key) {
        int index = key % size;
        List<KV<Integer, Integer>> list = map[index];
        if (list == null) {
            list = new LinkedList<>();
        }
        for (KV<Integer, Integer> item : list) {
            if (item.key == key) {
                return item.value;
            }
        }
        return -1;
    }

    /**
     * Removes the mapping of the specified value key if this map contains a mapping for the key
     */
    public void remove(int key) {
        int index = key % size;
        synchronized (MyHashMap.class) {
            List<KV<Integer, Integer>> list = map[index];
            if (list == null) {
                list = new LinkedList<>();
            }
            KV<Integer, Integer> kv = new KV<>();
            kv.key = key;
            kv.value = -1;
            list.remove(kv);
        }
    }

    private static class KV<K, V> {
        K key;
        V value;

        @Override
        public boolean equals(Object o) {
            return o instanceof KV && ((KV) o).key == this.key;
        }
    }
}
