package com.liyongquan.linklist;

import java.util.List;

/**
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 * <p>
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * <p>
 *  
 * <p>
 * 示例:
 * <p>
 * 给定 1->2->3->4, 你应该返回 2->1->4->3.
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/swap-nodes-in-pairs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SwapPairs {
    /**
     * 增加一个哑节点简化处理
     *
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        if (head == null) {
            return null;
        }
        //哑节点
        ListNode tmp = new ListNode(0);
        tmp.next = head;
        ListNode p1, p2, p3;
        p1 = tmp;
        while (p1 != null && p1.next != null && p1.next.next != null) {
            p2 = p1.next;
            p3 = p2.next;
            //交换
            ListNode p4 = p3.next;
            p1.next = p3;
            p3.next = p2;
            p2.next = p4;
            p1 = p2;
        }
        return tmp.next;
    }
}
