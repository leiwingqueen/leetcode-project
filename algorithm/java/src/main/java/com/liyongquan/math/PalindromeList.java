package com.liyongquan.math;

import com.liyongquan.linklist.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 请判断一个链表是否为回文链表。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 1->2
 * 输出: false
 * 示例 2:
 * <p>
 * 输入: 1->2->2->1
 * 输出: true
 * 进阶：
 * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-linked-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class PalindromeList {
    /**
     * 这解法的性能不好，关键问题在于arraylist的随机访问性能虽好，但是一开始的add操作是需要不断扩容的，扩容涉及的空间的重新分配
     *
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        if (list.isEmpty()) {
            return true;
        }
        for (int i = 0; i < list.size() / 2; i++) {
            if (list.get(i).intValue() != list.get(list.size() - 1 - i).intValue()) {
                return false;
            }
        }
        return true;
    }

    /**
     * 思路：
     * 1.计算链表长度
     * 2.翻转前半部分链表。
     * 比如 1->2->3->3->2->1,将其翻转为1<-2<-3 3->2->1。链表长度为奇数时不用管最中间的那个节点。
     * 3.从两个子链表的head开始，一一比较节点值，如果有不一样，就返回false，全部一样返回true
     * 时间复杂度O(n),空间复杂度O(1)
     * <p>
     * 作者：xueluoxiaohan
     * 链接：https://leetcode-cn.com/problems/palindrome-linked-list-lcci/solution/java-shi-jian-999-kong-jian-60-by-xueluoxiaohan/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public boolean isPalindrome2(ListNode head) {
        //计算链表长度
        int size = 0;
        for (ListNode node = head; node != null; node = node.next) {
            size++;
        }
        if (size <= 1) {
            return true;
        }
        //翻转
        ListNode node = head, next = head.next, pre = null;
        for (int i = 0; i < size / 2; i++) {
            node.next = pre;
            pre = node;
            node = next;
            next = next.next;
        }
        ListNode p1 = pre, p2;
        if ((size & 1) == 1) {
            p2 = next;
        } else {
            p2 = node;
        }
        //比较
        while (p1 != null && p2 != null) {
            if (p1.val != p2.val) {
                return false;
            }
            p1 = p1.next;
            p2 = p2.next;
        }
        return true;
    }
}
