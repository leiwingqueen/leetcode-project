package com.liyongquan.hash;

import java.util.LinkedList;
import java.util.List;

/**
 * 还是先不增加自动扩容，hashmap的核心概念还是桶和哈希冲突
 */
public class MyHashMap3 {
    //优先取素数
    public static final int KEY_SIZE = 2069;

    private Bucket[] hashTable;

    /**
     * Initialize your data structure here.
     */
    public MyHashMap3() {
        hashTable = new Bucket[KEY_SIZE];
    }

    /**
     * value will always be non-negative.
     */
    public void put(int key, int value) {
        Bucket bucket = hashTable[key % KEY_SIZE];
        if (bucket == null) {
            hashTable[key % KEY_SIZE] = new Bucket();
        }
        hashTable[key % KEY_SIZE].insert(key, value);
    }

    /**
     * Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key
     */
    public int get(int key) {
        return hashTable[key % KEY_SIZE] == null ? -1 : hashTable[key % KEY_SIZE].get(key);
    }

    /**
     * Removes the mapping of the specified value key if this map contains a mapping for the key
     */
    public void remove(int key) {
        if (hashTable[key % KEY_SIZE] != null) {
            hashTable[key % KEY_SIZE].remove(key);
        }
    }

    private static class KV {
        private int key;
        private int value;

        public KV(int key, int value) {
            this.key = key;
            this.value = value;
        }

        public int getKey() {
            return key;
        }

        public void setKey(int key) {
            this.key = key;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    private static class Bucket {
        //java8这个list已经改为使用tree的结构
        private List<KV> list;

        public Bucket() {
            list = new LinkedList<>();
        }

        public void insert(int key, int value) {
            for (KV kv : list) {
                if (kv.getKey() == key) {
                    kv.setValue(value);
                    return;
                }
            }
            list.add(new KV(key, value));
        }

        public int get(int key) {
            for (KV pair : list) {
                if (pair.getKey() == key) {
                    return pair.getValue();
                }
            }
            return -1;
        }

        public void remove(int key) {
            for (KV kv : list) {
                if (kv.getKey() == key) {
                    //这里删除也要扫描一遍，最好直接重写equals方法
                    list.remove(kv);
                    return;
                }
            }
        }
    }
}
