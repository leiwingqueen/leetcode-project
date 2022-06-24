package com.liyongquan.linklist;

/**
 * 删除链表中等于给定值 val 的所有节点。
 * <p>
 * 示例:
 * <p>
 * 输入: 1->2->6->3->4->5->6, val = 6
 * 输出: 1->2->3->4->5
 */
public class RemoveElements {
    /**
     * 增加一个哑节点，简化第一个节点的处理(哨兵)
     *
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements(ListNode head, int val) {
        ListNode p = new ListNode(0);
        p.next = head;
        ListNode pre = p, cur = head;
        while (cur != null) {
            if (cur.val == val) {
                pre.next = cur.next;
            }else{
                pre=pre.next;
            }
            cur = cur.next;
        }
        return p.next;
    }
}
