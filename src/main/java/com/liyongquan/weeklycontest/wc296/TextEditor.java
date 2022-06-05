package com.liyongquan.weeklycontest.wc296;

import com.liyongquan.tree.Node;
import com.sun.xml.internal.txw2.TXW;

public class TextEditor {
    Node head;
    //Node tail;
    Node cursor;

    public TextEditor() {
        head = new Node(' ');
        //tail = head;
        cursor = head;
    }

    public void addText(String text) {
        for (int i = 0; i < text.length(); i++) {
            Node node = new Node(text.charAt(i));
            Node next = cursor.next;
            cursor.next = node;
            node.pre = cursor;
            if (next != null) {
                node.next = next;
                next.pre = node;
            }
            cursor = cursor.next;
        }
    }

    public int deleteText(int k) {
        int cnt = 0;
        for (int i = 0; i < k; i++) {
            if (cursor.ch == ' ') {
                break;
            }
            Node pre = cursor.pre;
            Node next = cursor.next;
            pre.next = next;
            if (next != null) {
                next.pre = pre;
            }
            cursor = pre;
            cnt++;
        }
        return cnt;
    }

    public String cursorLeft(int k) {
        for (int i = 0; i < k; i++) {
            if (cursor.ch == ' ') {
                break;
            }
            cursor = cursor.pre;
        }
        StringBuilder sb = new StringBuilder();
        Node node = cursor;
        int cnt = 0;
        while (cnt < 10 && node.ch != ' ') {
            sb.append(node.ch);
            node = node.pre;
            cnt++;
        }
        return sb.reverse().toString();
    }

    public String cursorRight(int k) {
        for (int i = 0; i < k; i++) {
            if (cursor.next == null) {
                break;
            }
            cursor = cursor.next;
        }
        StringBuilder sb = new StringBuilder();
        Node node = cursor;
        int cnt = 0;
        while (cnt < 10 && node.ch != ' ') {
            sb.append(node.ch);
            node = node.pre;
            cnt++;
        }
        return sb.reverse().toString();
    }

    private static class Node {
        Node pre;
        Node next;
        char ch;

        public Node(char ch) {
            this.ch = ch;
        }
    }
}
