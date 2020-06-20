package com.liyongquan.linklist;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 * <p>
 * 示例：
 * <p>
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 * <p>
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 * 说明：
 * <p>
 * 给定的 n 保证是有效的。
 * <p>
 * 进阶：
 * <p>
 * 你能尝试使用一趟扫描实现吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class RemoveNode {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return null;
        }
        //使用一个辅助的list用来保存列表的位置信息
        List<ListNode> list = new ArrayList<>(2*n);
        ListNode cur = head;
        while (cur != null) {
            list.add(cur);
            cur = cur.next;
        }
        int size = list.size();
        if (size == 1) {
            return null;
        }
        if (n == 1) {
            list.get(size - 1 - n).next = null;
            return head;
        } else if (n == size) {
            return head.next;
        } else {
            list.get(size - 1 - n).next = list.get(size - n + 1);
            return head;
        }
    }

}
