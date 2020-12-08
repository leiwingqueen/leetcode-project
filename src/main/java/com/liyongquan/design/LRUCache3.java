package com.liyongquan.design;

import java.util.HashMap;

/**
 * 重新自己实现一遍
 */
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
        //dummy node，关键点，增加这两个dummy节点会简单很多
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.pre = head;
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        Node node = map.get(key);
        //删除node节点。list.remove(node);
        remove(node);
        //添加node节点。list.addFirst(node);
        addFirst(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            //list.remove(map.get(key));
            //删除node节点。list.remove(node);
            remove(map.get(key));
        } else if (size >= capacity) {
            //删除最后一个节点
            Node del = tail.pre;
            remove(del);
            map.remove(del.key);
        }
        Node node = new Node(key, value);
        addFirst(node);
        map.put(key, node);
    }

    /***********************下面几个为实现双向链表的方法**************************/

    private void remove(Node node) {
        Node pre = node.pre;
        Node next = node.next;
        pre.next = next;
        next.pre = pre;

        node.pre = null;
        node.next = null;
        size--;
    }

    private void addFirst(Node node) {
        Node next = head.next;
        head.next = node;
        node.pre = head;
        node.next = next;
        next.pre = node;
        size++;
    }
}
