package com.liyongquan.linklist;

public class YuanQuan {
    public int lastRemaining(int n, int m) {
        //构造环状链表
        Node head=new Node();
        head.value=0;
        Node cur=head;
        for (int i = 1; i < n; i++) {
            Node node=new Node();
            node.value=i;
            cur.next=node;
            node.pre=cur;
            cur=cur.next;
        }
        cur.next=head;
        head.pre=cur;
        //扫描
        int length=n;
        cur=head;
        while (length>1){
            for (int i = 0; i < m-1; i++) {
                cur=cur.next;
            }
            System.out.println("删除结点:"+cur.value);
            Node pre = cur.pre;
            Node next = cur.next;
            pre.next=next;
            next.pre=pre;
            cur=next;
            length--;
        }
        return cur.value;
    }

    class Node{
        int value;
        Node next;
        Node pre;
    }

    public static void main(String[] args) {
        YuanQuan yuanQuan=new YuanQuan();
        int i = yuanQuan.lastRemaining(5, 2);
        System.out.println(i);
    }
}
