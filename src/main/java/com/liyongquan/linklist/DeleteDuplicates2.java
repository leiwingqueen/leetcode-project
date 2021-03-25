package com.liyongquan.linklist;

/**
 * 82. 删除排序链表中的重复元素 II
 * <p>
 * 给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 1->2->3->3->4->4->5
 * 输出: 1->2->5
 * 示例 2:
 * <p>
 * 输入: 1->1->1->2->3
 * 输出: 2->3
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class DeleteDuplicates2 {
    /**
     * 解法1
     *
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy, cur = head;
        while (cur != null) {
            //删除后面重复的元素
            boolean del = false;
            while (cur.next != null && cur.next.val == cur.val) {
                //删除cur.next的结点
                ListNode next = cur.next;
                cur.next = next.next;
                next.next = null;
                del = true;
            }
            //如果重复，当前结点也需要删除
            if (del) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = pre.next;
        }
        return dummy.next;
    }

    /**
     * 代码优化
     *
     * @param head
     * @return
     */
    public ListNode deleteDuplicates2(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = dummy;
        while (cur != null) {
            //检查cur.next和cur.next.next是否相同
            if (cur.next != null && cur.next.next != null && cur.next.val == cur.next.next.val) {
                //删除
                int num = cur.next.val;
                cur.next = cur.next.next.next;
                //检查后面的结点是否有相同的结点，有的话继续删除
                while (cur.next != null && cur.next.val == num) {
                    cur.next = cur.next.next;
                }
            } else {
                cur = cur.next;
            }
        }
        return dummy.next;
    }

    /**
     * 工程写法，需要断开不必要的指针，避免无法GC，只是这样写看起来会比较繁琐
     *
     * @param head
     * @return
     */
    public ListNode deleteDuplicates3(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = dummy;
        while (cur != null) {
            //检查cur.next和cur.next.next是否相同
            if (cur.next != null && cur.next.next != null && cur.next.val == cur.next.next.val) {
                //删除
                int num = cur.next.val;
                ListNode n1 = cur.next, n2 = cur.next.next;
                cur.next = n2.next;
                n1.next = null;
                n2.next = null;
                //检查后面的结点是否有相同的结点，有的话继续删除
                while (cur.next != null && cur.next.val == num) {
                    ListNode delNode = cur.next;
                    cur.next = cur.next.next;
                    delNode.next = null;
                }
            } else {
                cur = cur.next;
            }
        }
        return dummy.next;
    }
}
