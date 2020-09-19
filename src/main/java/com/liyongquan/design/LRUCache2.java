package com.liyongquan.design;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * 用现成的库解题没太大意思，决定自己写一遍linklist+hashmap的解法
 * https://leetcode-cn.com/problems/lru-cache/solution/lru-ce-lue-xiang-jie-he-shi-xian-by-labuladong/
 */
public class LRUCache2 {
    private HashMap<Integer, Node> map;
    private LinkedList<Node> list;
    private int capacity;

    public LRUCache2(int capacity) {
        map = new HashMap<>(capacity);
        list = new LinkedList<>();
        this.capacity = capacity;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            //这里有点问题，linklist的删除指定key的效率是O(n),为了实现O(1)的删除效率，事实上我们要自己实现链表才行，在node结点定义前后指针
            Node node = map.get(key);
            list.remove(node);
            list.addFirst(node);
            return node.value;
        }
        return -1;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            //这里有点问题，linklist的删除指定key的效率是O(n)
            list.remove(map.get(key));
        } else if (list.size() >= capacity) {
            Node node = list.removeLast();
            map.remove(node.key);
        }
        Node node = new Node(key, value);
        list.addFirst(node);
        map.put(key, node);
    }

    class Node {
        int key;
        int value;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}
