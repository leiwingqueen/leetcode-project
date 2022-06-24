package com.liyongquan.linklist;

/**
 * 给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。
 * <p>
 * 返回删除后的链表的头节点。
 * <p>
 * 注意：此题对比原题有改动
 * <p>
 * 示例 1:
 * <p>
 * 输入: head = [4,5,1,9], val = 5
 * 输出: [4,1,9]
 * 解释: 给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.
 * <p>
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shan-chu-lian-biao-de-jie-dian-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class DeleteNode {
    public ListNode deleteNode(ListNode head, int val) {
        ListNode cur=head;
        ListNode before=null;
        while (cur!=null){
            if (cur.val==val) {
                if (before!=null) {
                    before.next=cur.next;
                }else{
                    head=cur.next;
                }
                break;
            }
            before=cur;
            cur=cur.next;
        }
        return head;
    }
}
