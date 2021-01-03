package com.liyongquan.linklist;

/**
 * 给你一个链表和一个特定值 x ，请你对链表进行分隔，使得所有小于 x 的节点都出现在大于或等于 x 的节点之前。
 * <p>
 * 你应当保留两个分区中每个节点的初始相对位置。
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * 输入：head = 1->4->3->2->5->2, x = 3
 * 输出：1->2->2->4->3->5
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/partition-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class PartitionList2 {
    /**
     * 找到一个pivot节点。把所有小于x的节点都移动到pivot节点之后，并更新pivot节点
     *
     * @param head
     * @param x
     * @return
     */
    public ListNode partition(ListNode head, int x) {
        if (head == null) {
            return null;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        //找到pivot节点
        ListNode pivot = null, cur = dummy;
        while (cur.next != null && cur.next.val < x) {
            cur = cur.next;
        }
        if (cur.next == null) {
            return head;
        }
        pivot = cur;
        //从pivot节点往后找到>=x的节点
        ListNode pivotNext = pivot.next;
        while (cur != null && cur.next != null) {
            if (cur.next.val < x) {
                //把节点移动到pivot后面
                ListNode node = cur.next;
                cur.next = node.next;
                pivot.next = node;
                node.next = pivotNext;
                pivot = pivot.next;
            }else {
                cur = cur.next;
            }
        }
        return dummy.next;
    }
}
