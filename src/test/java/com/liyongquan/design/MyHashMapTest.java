package com.liyongquan.design;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MyHashMapTest {
    MyHashMap hashMap = new MyHashMap();

    @Test
    public void put() {
        MyHashMap hashMap = new MyHashMap();
        hashMap.put(1, 1);
        hashMap.put(2, 2);
        int i = hashMap.get(1);// 返回 1
        assertEquals(1, i);
        int i1 = hashMap.get(3);// 返回 -1 (未找到)
        assertEquals(-1, i1);
        hashMap.put(2, 1); // 更新已有的值
        hashMap.get(2); // 返回 1
        hashMap.remove(2);// 删除键为2的数据
        int i2 = hashMap.get(2);// 返回 -1 (未找到)
        assertEquals(-1, i2);
    }

    @Test
    public void put2() {
        MyHashMap hashMap = new MyHashMap();
        for (int i = 0; i < 100; i++) {
            hashMap.put(i, i);
        }
        for (int i = 0; i < 100; i++) {
            assertEquals(i, hashMap.get(i));
        }
    }

    /**
     * 哈希碰撞
     */
    @Test
    public void put3() {
        MyHashMap hashMap = new MyHashMap();
        hashMap.put(0, 0);
        hashMap.put(16, 16);
        assertEquals(0, hashMap.get(0));
        assertEquals(16, hashMap.get(16));
    }

    @Test
    public void get() {
    }

    @Test
    public void remove() {
    }
}