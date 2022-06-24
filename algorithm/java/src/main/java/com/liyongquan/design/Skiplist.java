package com.liyongquan.design;

/**
 * 1206. 设计跳表
 * <p>
 * 不使用任何库函数，设计一个跳表。
 * <p>
 * 跳表是在 O(log(n)) 时间内完成增加、删除、搜索操作的数据结构。跳表相比于树堆与红黑树，其功能与性能相当，并且跳表的代码长度相较下更短，其设计思想与链表相似。
 * <p>
 * 例如，一个跳表包含 [30, 40, 50, 60, 70, 90]，然后增加 80、45 到跳表中，以下图的方式操作：
 * <p>
 * <p>
 * Artyom Kalinin [CC BY-SA 3.0], via Wikimedia Commons
 * <p>
 * 跳表中有很多层，每一层是一个短的链表。在第一层的作用下，增加、删除和搜索操作的时间复杂度不超过 O(n)。跳表的每一个操作的平均时间复杂度是 O(log(n))，空间复杂度是 O(n)。
 * <p>
 * 在本题中，你的设计应该要包含这些函数：
 * <p>
 * bool search(int target) : 返回target是否存在于跳表中。
 * void add(int num): 插入一个元素到跳表。
 * bool erase(int num): 在跳表中删除一个值，如果 num 不存在，直接返回false. 如果存在多个 num ，删除其中任意一个即可。
 * 了解更多 : https://en.wikipedia.org/wiki/Skip_list
 * <p>
 * 注意，跳表中可能存在多个相同的值，你的代码需要处理这种情况。
 * <p>
 * 样例:
 * <p>
 * Skiplist skiplist = new Skiplist();
 * <p>
 * skiplist.add(1);
 * skiplist.add(2);
 * skiplist.add(3);
 * skiplist.search(0);   // 返回 false
 * skiplist.add(4);
 * skiplist.search(1);   // 返回 true
 * skiplist.erase(0);    // 返回 false，0 不在跳表中
 * skiplist.erase(1);    // 返回 true
 * skiplist.search(1);   // 返回 false，1 已被擦除
 * 约束条件:
 * <p>
 * 0 <= num, target <= 20000
 * 最多调用 50000 次 search, add, 以及 erase操作。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/design-skiplist
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

/**
 * 这篇文章讲得还可以
 * https://www.jianshu.com/p/9d8296562806
 */
public class Skiplist {
    //算上原链表
    public static final int LEVEL = 4;
    //N级索引
    private ListNode[] list;

    public Skiplist() {
        list = new ListNode[LEVEL];
        //头结点是一个dummy结点
        for (int i = 0; i < LEVEL; i++) {
            list[i] = new ListNode(-1);
        }
        //初始化down
        for (int i = LEVEL - 1; i > 0; i--) {
            list[i].down = list[i - 1];
        }
    }

    public boolean search(int target) {
        int lv = LEVEL - 1;
        ListNode cur = list[lv];
        //索引查找
        while (lv > 0) {
            while (cur.next != null && cur.next.val < target) {
                cur = cur.next;
            }
            if (cur.next != null && cur.next.val == target) {
                return true;
            }
            //向下查找
            cur = cur.down;
            lv--;
        }
        //线性查找
        while (cur.next != null && cur.next.val < target) {
            cur = cur.next;
        }
        return cur.next != null && cur.next.val == target;
    }

    public void add(int num) {
        int rand = randomLevel();
        int lv = LEVEL - 1;
        ListNode cur = list[lv];
        //索引查找
        ListNode pre = null;
        while (lv > 0) {
            while (cur.next != null && cur.next.val < num) {
                cur = cur.next;
            }
            //增加索引结点
            if (rand >= lv) {
                ListNode next = cur.next;
                ListNode node = new ListNode(num);
                cur.next = node;
                node.next = next;
                //链接上上一层的节点
                if (pre != null) {
                    pre.down = node;
                }
                pre = node;
            }
            cur = cur.down;
            lv--;
        }
        //线性查找
        while (cur.next != null && cur.next.val < num) {
            cur = cur.next;
        }
        ListNode next = cur.next;
        ListNode node = new ListNode(num);
        cur.next = node;
        node.next = next;
        //链接上上一层的节点
        if (pre != null) {
            pre.down = node;
        }
    }

    public boolean erase(int num) {
        int lv = LEVEL - 1;
        ListNode cur = list[lv];
        while (lv > 0) {
            //向右查找
            while (cur.next != null && cur.next.val < num) {
                cur = cur.next;
            }
            //向下查找
            if (cur.next != null && cur.next.val == num) {
                //删除节点
                cur.next = cur.next.next;
            }
            cur = cur.down;
            lv--;
        }
        //线性查找
        while (cur.next != null && cur.next.val < num) {
            cur = cur.next;
        }
        if (cur.next != null && cur.next.val == num) {
            cur.next = cur.next.next;
            return true;
        }
        return false;
    }

    /**
     * 索引随机，第一层索引概率0.5,第二层索引概率0.25,每往上一层概率为原来的一半。在概率分布上我们可以认为上一层的节点是下一层节点的一半
     *
     * @return
     */
    private int randomLevel() {
        int level = 0;
        if (Math.random() < 0.5 && level < LEVEL - 1) {
            level++;
        }
        return level;
    }

    private static class ListNode {
        int val;
        ListNode next;
        ListNode down;

        public ListNode(int val) {
            this.val = val;
        }
    }
}
