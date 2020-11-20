package com.liyongquan.linklist;

/**
 * 对链表进行插入排序。
 * <p>
 * <p>
 * 插入排序的动画演示如上。从第一个元素开始，该链表可以被认为已经部分排序（用黑色表示）。
 * 每次迭代时，从输入数据中移除一个元素（用红色表示），并原地将其插入到已排好序的链表中。
 * <p>
 *  
 * <p>
 * 插入排序算法：
 * <p>
 * 插入排序是迭代的，每次只移动一个元素，直到所有元素可以形成一个有序的输出列表。
 * 每次迭代中，插入排序只从输入数据中移除一个待排序的元素，找到它在序列中适当的位置，并将其插入。
 * 重复直到所有输入数据插入完为止。
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入: 4->2->1->3
 * 输出: 1->2->3->4
 * 示例 2：
 * <p>
 * 输入: -1->5->3->4->0
 * 输出: -1->0->3->4->5
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/insertion-sort-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class InsertionSortList {
    /**
     * 按照官方给的说明实现一遍插排
     * <p>
     * 时间复杂度O(n^2)，最坏的情况是排序一个已经排好的链表，每次都要对已经排好的整个链表进行扫描，最好的情况逆序排好的链表(O(n))，永远把数字放在头部就可以了
     * <p>
     * 空间复杂度O(1)
     *
     * @param head
     * @return
     */
    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        //分为两个list，一个是已经排序好的，另外一个是未排序的
        ListNode unsorted = head.next;
        head.next = null;
        return recursive(head, unsorted);
    }

    private ListNode recursive(ListNode sort, ListNode unsorted) {
        if (unsorted == null) {
            return sort;
        }
        //要排序的节点
        ListNode node = unsorted;
        unsorted = unsorted.next;
        node.next = null;
        //找到插入的位置
        ListNode cur = sort;
        ListNode sentinel = new ListNode(0);
        ListNode pre = sentinel;
        pre.next = sort;
        while (cur != null && cur.val <= node.val) {
            pre = cur;
            cur = cur.next;
        }
        //插入
        if (pre.next == null) {
            pre.next = node;
        } else {
            ListNode next = pre.next;
            pre.next = node;
            node.next = next;
        }
        //递归下一层
        return recursive(sentinel.next, unsorted);
    }

    /**
     * 作者：LeetCode-Solution
     *     链接：https://leetcode-cn.com/problems/insertion-sort-list/solution/dui-lian-biao-jin-xing-cha-ru-pai-xu-by-leetcode-s/
     *     来源：力扣（LeetCode）
     *     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     *     这个写法简洁好多
     * @param head
     * @return
     */
    public ListNode insertionSortList2(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode lastSorted = head, curr = head.next;
        while (curr != null) {
            if (lastSorted.val <= curr.val) {
                lastSorted = lastSorted.next;
            } else {
                ListNode prev = dummyHead;
                while (prev.next.val <= curr.val) {
                    prev = prev.next;
                }
                lastSorted.next = curr.next;
                curr.next = prev.next;
                prev.next = curr;
            }
            curr = lastSorted.next;
        }
        return dummyHead.next;
    }
}
