package com.liyongquan.linklist;

import java.util.Stack;

/**
 * 输入两个链表，找出它们的第一个公共节点。
 *
 * 如下面的两个链表：
 *
 * 在节点 c1 开始相交。
 *
 *
 *
 * 示例 1：
 *
 * 输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
 * 输出：Reference of the node with value = 8
 * 输入解释：相交节点的值为 8 （注意，如果两个列表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
 *
 *
 *
 * 示例 2：
 *
 * 输入：intersectVal = 2, listA = [0,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
 * 输出：Reference of the node with value = 2
 * 输入解释：相交节点的值为 2 （注意，如果两个列表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [0,9,1,2,4]，链表 B 为 [3,2,4]。在 A 中，相交节点前有 3 个节点；在 B 中，相交节点前有 1 个节点。
 *
 *
 *
 * 示例 3：
 *
 * 输入：intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
 * 输出：null
 * 输入解释：从各自的表头开始算起，链表 A 为 [2,6,4]，链表 B 为 [1,5]。由于这两个链表不相交，所以 intersectVal 必须为 0，而 skipA 和 skipB 可以是任意值。
 * 解释：这两个链表不相交，因此返回 null。
 *
 *
 *
 * 注意：
 *
 *     如果两个链表没有交点，返回 null.
 *     在返回结果后，两个链表仍须保持原有的结构。
 *     可假定整个链表结构中没有循环。
 *     程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。
 *     本题与主站 160 题相同：https://leetcode-cn.com/problems/intersection-of-two-linked-lists/
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/liang-ge-lian-biao-de-di-yi-ge-gong-gong-jie-dian-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class IntersectionNode {
    /**
     * 采用双栈的方式更直观
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Stack<ListNode> s1=new Stack<>();
        Stack<ListNode> s2=new Stack<>();
        while (headA!=null){
            s1.push(headA);
            headA=headA.next;
        }
        while (headB!=null){
            s2.push(headB);
            headB=headB.next;
        }
        ListNode result=null;
        while (!s1.isEmpty()&&!s2.isEmpty()){
            ListNode pop1 = s1.pop();
            ListNode pop2 = s2.pop();
            if (pop1==pop2) {
                result=pop1;
            }else {
                break;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        /**
         * [4,1,8,4,5]
         * [5,0,1,8,4,5]
         */
        ListNode sub=new ListNode(8);
        ListNode n1=new ListNode(4);
        ListNode n2=new ListNode(5);
        sub.next=n1;
        n1.next=n2;
        ListNode headA=new ListNode(2);
        ListNode headB=new ListNode(7);
        headA.next=sub;
        headB.next=sub;
        IntersectionNode intersectionNode=new IntersectionNode();
        ListNode intersectionNode1 = intersectionNode.getIntersectionNode(headA, headB);
        System.out.println(intersectionNode1.val);
    }
}
