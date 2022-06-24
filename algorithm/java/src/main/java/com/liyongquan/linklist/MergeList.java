package com.liyongquan.linklist;

/**
 * 输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的。
 *
 * 示例1：
 *
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 *
 * 限制：
 *
 * 0 <= 链表长度 <= 1000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/he-bing-liang-ge-pai-xu-de-lian-biao-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MergeList {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1==null) {
            return l2;
        }
        if (l2==null) {
            return l1;
        }
        ListNode c1=l1;
        ListNode c2=l2;
        ListNode head = null,cur=null;
        while (c1!=null&&c2!=null){
            ListNode node;
            if(c1.val<c2.val){
                node=new ListNode(c1.val);
                c1=c1.next;
            }else {
                node=new ListNode(c2.val);
                c2=c2.next;
            }
            if (head==null) {
                head=node;
                cur=node;
            }else{
                cur.next=node;
                cur=cur.next;
            }
        }
        //假如其中一个已经为空，我们直接把剩余的结点链到cur
        if (c1!=null) {
            cur.next=c1;
        }else if(c2!=null){
            cur.next=c2;
        }
        return head;
    }
}
