package com.liyongquan.array;

import com.liyongquan.linklist.ListNode;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 编写代码，移除未排序链表中的重复节点。保留最开始出现的节点。
 * <p>
 * 示例1:
 * <p>
 * 输入：[1, 2, 3, 3, 2, 1]
 * 输出：[1, 2, 3]
 * 示例2:
 * <p>
 * 输入：[1, 1, 1, 1, 2]
 * 输出：[1, 2]
 * 提示：
 * <p>
 * 链表长度在[0, 20000]范围内。
 * 链表元素在[0, 20000]范围内。
 * 进阶：
 * <p>
 * 如果不得使用临时缓冲区，该怎么解决？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-duplicate-node-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class RemoveDuplicateNodes {
    public ListNode removeDuplicateNodes(ListNode head) {
        if (head == null) {
            return null;
        }
        Set<Integer> set = new HashSet<>();
        ListNode sentinel = new ListNode(0);
        sentinel.next = head;
        ListNode node = head, pre = sentinel;
        while (node != null) {
            if (set.contains(node.val)) {
                pre.next = node.next;
            } else {
                set.add(node.val);
                pre = node;
            }
            node = node.next;
        }
        return sentinel.next;
    }

    public static void main(String[] args) {
        int[] list = new int[]{1, 2, 3, 3, 2, 1};
        ListNode head = new ListNode(list[0]);
        ListNode pre = head;
        for (int i = 1; i < list.length; i++) {
            ListNode node = new ListNode(list[i]);
            pre.next = node;
            pre = node;
        }
        RemoveDuplicateNodes rd = new RemoveDuplicateNodes();
        ListNode ln = rd.removeDuplicateNodes(head);
        while (ln != null) {
            System.out.println(ln.val);
            ln = ln.next;
        }
    }
}
