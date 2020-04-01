package com.liyongquan.linklist;

public class AddTwoNumber {
    /**
     * Definition for singly-linked list.
     */
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static class Solution {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode head=new ListNode(0);
            ListNode p3=head;
            ListNode p1=l1;
            ListNode p2=l2;
            while (p1!=null||p2!=null){
                int sum=(p1==null?0:p1.val)+(p2==null?0:p2.val)+p3.val;
                int up=0;
                if (sum>=10) {
                    sum=sum-10;
                    up=1;
                }
                p3.val=sum;
                if (p1!=null) {
                    p1=p1.next;
                }
                if (p2!=null) {
                    p2=p2.next;
                }
                if (p1!=null||p2!=null||up>0) {
                    p3.next = new ListNode(up);
                    p3 = p3.next;
                }
            };
            return head;
        }
    }

    public static void main(String[] args) {
        ListNode l1 = convert(new int[]{2, 4, 3});
        ListNode l2 = convert(new int[]{5, 6, 4});
        Solution solution=new Solution();
        ListNode listNode = solution.addTwoNumbers(l1, l2);
        while (listNode!=null){
            System.out.println(listNode.val);
            listNode=listNode.next;
        }
    }




    public static ListNode convert(int[] a){
        ListNode head=new ListNode(a[0]);
        ListNode c=head;
        for (int i = 1; i < a.length; i++) {
            c.next=new ListNode(a[i]);
            c=c.next;
        }
        return head;
    }
}
