package com.liyongquan.linklist;

import java.util.ArrayList;
import java.util.List;

/**
 * 输入一个链表，输出该链表中倒数第k个节点。
 * 为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。例如，
 * 一个链表有6个节点，从头节点开始，它们的值依次是1、2、3、4、5、6。这个链表的倒数第3个节点是值为4的节点。
 * <p>
 * <p>
 * <p>
 * 示例：
 * <p>
 * 给定一个链表: 1->2->3->4->5, 和 k = 2.
 * <p>
 * 返回链表 4->5.
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class KNode {
    /**
     * 扫描列表，计算得到链表的长度。然后顺序访问
     * @param head
     * @param k
     * @return
     */
    public ListNode getKthFromEnd2(ListNode head, int k) {
        int length=0;
        ListNode cur=head;
        while (cur!=null){
            cur=cur.next;
            length++;
        }
        int index=length-k;
        cur=head;
        while (index>0){
            cur=cur.next;
            index--;
        }
        return cur;
    }

    /**
     * 第二种做法，使用空间换时间，使用一个数组记录每个结点的位置
     * @param head
     * @param k
     * @return
     */
    public ListNode getKthFromEnd(ListNode head, int k) {
        List<ListNode> listNodes=new ArrayList<>();
        ListNode cur=head;
        while (cur!=null){
            listNodes.add(cur);
            cur=cur.next;
        }
        return listNodes.get(listNodes.size()-k);
    }

}
