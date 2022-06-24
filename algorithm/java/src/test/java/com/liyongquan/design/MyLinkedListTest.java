package com.liyongquan.design;

import org.junit.Test;

import static org.junit.Assert.*;

public class MyLinkedListTest {
    /**
     * ["MyLinkedList","addAtHead","addAtHead","addAtHead","addAtIndex","deleteAtIndex","addAtHead","addAtTail","get","addAtHead","addAtIndex","addAtHead"]
     * [[],[7],[2],[1],[3,0],[2],[6],[4],[4],[4],[5,0],[6]]
     */
    @Test
    public void test() {
        MyLinkedList ml = new MyLinkedList();
        ml.addAtHead(7);
        ml.addAtHead(2);
        ml.addAtHead(1);
        ml.addAtIndex(3, 0);
        ml.deleteAtIndex(2);
        ml.addAtHead(6);
        ml.addAtTail(4);
        int i = ml.get(4);
        System.out.println(i);
        ml.addAtHead(4);
        ml.addAtIndex(5, 0);
        ml.addAtHead(6);
    }

    /**
     * ["MyLinkedList","addAtHead","addAtTail","addAtIndex","get","deleteAtIndex","get"]
     * [[],[1],[3],[1,2],[1],[1],[1]]
     */
    @Test
    public void test2() {
        MyLinkedList ml = new MyLinkedList();
        ml.addAtHead(1);
        ml.addAtTail(3);
        ml.addAtIndex(1, 2);
        int g = ml.get(1);
        System.out.println(g);
        ml.deleteAtIndex(1);
        int i = ml.get(1);
        System.out.println(i);
    }
}