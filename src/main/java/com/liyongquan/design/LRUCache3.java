package com.liyongquan.design;

import java.util.HashMap;

public class LRUCache3 {
    private HashMap<Integer, Node> map;
    private int capacity;
    private int size;
    private Node head;
    private Node tail;


    public LRUCache3(int capacity) {
        map = new HashMap<>(capacity);
        this.capacity = capacity;
        size = 0;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            //删除node节点。list.remove(node);
            remove(node);
            //添加node节点。list.addFirst(node);
            addFirst(node);
            return node.value;
        }
        return -1;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            //list.remove(map.get(key));
            //删除node节点。list.remove(node);
            remove(map.get(key));
        } else if (size >= capacity) {
            Node node = removeLast();
            map.remove(node.key);
        }
        Node node = new Node(key, value);
        addFirst(node);
        map.put(key, node);
    }

    /***********************下面几个为实现双向链表的方法**************************/

    private void remove(Node node) {
        //头部节点
        if (node.pre == null) {
            head = node.next;
            if (head != null) {
                head.pre = null;
            }
        } else {
            node.pre.next = node.next;
            if (node.next != null) {
                node.next.pre = node.pre;
            }
        }
        if (node.next == null) {
            tail = node.pre;
        }
        size--;
    }

    private void addFirst(Node node) {
        if (head == null) {
            tail = node;
        } else {
            node.next = head;
            head.pre = node;
        }
        head = node;
        size++;
    }

    private Node removeLast() {
        if (tail == null) {
            return null;
        }
        Node last = tail;
        tail = tail.pre;
        if (tail != null) {
            tail.next = null;
        }
        size--;
        return last;
    }
}
