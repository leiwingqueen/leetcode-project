package com.liyongquan;

import java.util.List;

public class MiddleLink {
    //Definition for singly-linked list.
    public static class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
    }
    static class Solution {
        public ListNode middleNode(ListNode head) {
            ListNode middle=head;
            int index=1;
            ListNode current=head;
            while(current.next!=null){
                current=current.next;
                index++;
                if (index==2) {
                    middle=current;
                }else if (index%2==0) {
                    middle=middle.next;
                }
            }
            return middle;
        }
    }

    public static void main(String[] args) {
        ListNode head=new ListNode(1);
        ListNode cr=head;
        for(int i=2;i<=6;i++){
            cr.next=new ListNode(i);
            cr=cr.next;
        }
        Solution solution=new Solution();
        ListNode middleNode = solution.middleNode(head);
        System.out.println(middleNode.val);
    }
}
