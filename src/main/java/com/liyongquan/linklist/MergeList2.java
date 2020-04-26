package com.liyongquan.linklist;

import java.util.LinkedList;
import java.util.List;

/**
 * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
 * <p>
 * 示例:
 * <p>
 * 输入:
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * 输出: 1->1->2->3->4->4->5->6
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-k-sorted-lists
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MergeList2 {
    /**
     * 时间复杂度 O(k*n)，假设有k个链表，每个链表的长度为n
     * 空间复杂度O(k)
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length <= 0) {
            return null;
        }
        List<ListNode> pts = new LinkedList<>();
        for (int i = 0; i < lists.length; i++) {
            pts.add(lists[i]);
        }
        ListNode result = null;
        ListNode cur = null;
        while (pts.size() > 0) {
            ListNode min = null;
            for (ListNode node : pts) {
                if (min == null || node.val < min.val) {
                    min = node;
                }
            }
            if (cur == null) {
                result = new ListNode(min.val);
                cur = result;
            } else {
                cur.next = new ListNode(min.val);
                cur = cur.next;
            }
            if (min.next == null) {
                pts.remove(min);
            }
            min = min.next;
        }
        return result;
    }

    public ListNode mergeKLists1(ListNode[] lists) {
        if (lists == null || lists.length <= 0) {
            return null;
        }
        ListNode[] pts = new ListNode[lists.length];
        for (int i = 0; i < lists.length; i++) {
            ListNode list = lists[i];
            pts[i] = lists[i];
        }
        ListNode result = null;
        ListNode cur = null;
        while (true) {
            ListNode min = pts[0];
            int index = 0;
            for (int i = 1; i < pts.length; i++) {
                if (pts[i] != null && (min == null || pts[i].val < min.val)) {
                    index = i;
                    min = pts[i];
                }
            }
            if (min == null) {
                break;
            }
            pts[index] = pts[index].next;
            if (cur == null) {
                result = new ListNode(min.val);
                cur = result;
            } else {
                cur.next = new ListNode(min.val);
                cur = cur.next;
            }
        }
        return result;
    }
}
