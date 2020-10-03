package com.liyongquan.linklist;

import java.util.List;

/**
 * 编写程序以 x 为基准分割链表，使得所有小于 x 的节点排在大于或等于 x 的节点之前。如果链表中包含 x，x 只需出现在小于 x 的元素之后(如下所示)。分割元素 x 只需处于“右半部分”即可，其不需要被置于左右两部分之间。
 * <p>
 * 示例:
 * <p>
 * 输入: head = 3->5->8->5->10->2->1, x = 5
 * 输出: 3->1->2->10->5->5->8
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/partition-list-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class PartitionList {
    /**
     * 直接把小于x的结点移到头部节点即可
     *
     * @param head
     * @param x
     * @return
     */
    public ListNode partition(ListNode head, int x) {
        if (head==null) {
            return null;
        }
        //首节点我们不需要移动，直接从第二个节点开始扫描
        ListNode node = head.next, pre = head;
        while (node != null) {
            if (node.val < x) {
                //移动到head
                pre.next = node.next;
                node.next = head;
                head = node;
                //继续往下扫描
                node = pre.next;
            } else {
                node = node.next;
                pre = pre.next;
            }
        }
        return head;
    }
}
