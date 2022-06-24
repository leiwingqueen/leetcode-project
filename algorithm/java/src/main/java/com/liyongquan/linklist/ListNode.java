package com.liyongquan.linklist;

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
    }

    /**
     * 自己定义的构造方法，方便跑单元测试
     */
    public static ListNode build(int[] nodes) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        for (int node : nodes) {
            cur.next = new ListNode(node);
            cur = cur.next;
        }
        return dummy.next;
    }
}
