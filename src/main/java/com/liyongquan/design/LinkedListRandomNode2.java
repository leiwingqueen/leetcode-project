package com.liyongquan.design;

import com.liyongquan.linklist.ListNode;

import java.util.Random;

//给你一个单链表，随机选择链表的一个节点，并返回相应的节点值。每个节点 被选中的概率一样 。
//
//实现 Solution 类：
//
//Solution(ListNode head) 使用整数数组初始化对象。
//int getRandom() 从链表中随机选择一个节点并返回该节点的值。链表中所有节点被选中的概率相等。
// 
//
//示例：
//
//
//输入
//["Solution", "getRandom", "getRandom", "getRandom", "getRandom", "getRandom"]
//[[[1, 2, 3]], [], [], [], [], []]
//输出
//[null, 1, 3, 2, 2, 3]
//
//解释
//Solution solution = new Solution([1, 2, 3]);
//solution.getRandom(); // 返回 1
//solution.getRandom(); // 返回 3
//solution.getRandom(); // 返回 2
//solution.getRandom(); // 返回 2
//solution.getRandom(); // 返回 3
//// getRandom() 方法应随机返回 1、2、3中的一个，每个元素被返回的概率相等。
// 
//
//提示：
//
//链表中的节点数在范围 [1, 104] 内
//-104 <= Node.val <= 104
//至多调用 getRandom 方法 104 次
// 
//
//进阶：
//
//如果链表非常大且长度未知，该怎么处理？
//你能否在不使用额外空间的情况下解决此问题？
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/linked-list-random-node
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

public class LinkedListRandomNode2 {
    private ListNode head;
    private Random random;

    public LinkedListRandomNode2(ListNode head) {
        this.head = head;
        random = new Random();
    }

    public int getRandom() {
        int idx = 0;
        int res = 0;
        ListNode cur = this.head;
        while (cur != null) {
            if (random.nextInt(idx + 1) == 0) {
                res = cur.val;
            }
            cur = cur.next;
            idx++;
        }
        return res;
    }
}
