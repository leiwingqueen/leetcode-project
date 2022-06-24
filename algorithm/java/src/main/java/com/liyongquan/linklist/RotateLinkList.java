package com.liyongquan.linklist;

import java.util.List;

/**
 * 定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。
 *
 *
 *
 * 示例:
 *
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 *
 *
 *
 * 限制：
 *
 * 0 <= 节点个数 <= 5000
 */
public class RotateLinkList {
    public ListNode reverseList(ListNode head) {
        ListNode cur= head;
        ListNode pre=null;
        while (cur!=null){
            ListNode node=new ListNode(cur.val);
            node.next=pre;
            pre=node;
            cur=cur.next;
        }
        return pre;
    }
}
