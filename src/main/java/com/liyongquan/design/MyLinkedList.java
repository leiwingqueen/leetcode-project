package com.liyongquan.design;

/**
 * 设计链表的实现。您可以选择使用单链表或双链表。单链表中的节点应该具有两个属性：val 和 next。val 是当前节点的值，next 是指向下一个节点的指针/引用。如果要使用双向链表，则还需要一个属性 prev 以指示链表中的上一个节点。假设链表中的所有节点都是 0-index 的。
 * <p>
 * 在链表类中实现这些功能：
 * <p>
 * get(index)：获取链表中第 index 个节点的值。如果索引无效，则返回-1。
 * addAtHead(val)：在链表的第一个元素之前添加一个值为 val 的节点。插入后，新节点将成为链表的第一个节点。
 * addAtTail(val)：将值为 val 的节点追加到链表的最后一个元素。
 * addAtIndex(index,val)：在链表中的第 index 个节点之前添加值为 val  的节点。如果 index 等于链表的长度，则该节点将附加到链表的末尾。如果 index 大于链表长度，则不会插入节点。如果index小于0，则在头部插入节点。
 * deleteAtIndex(index)：如果索引 index 有效，则删除链表中的第 index 个节点。
 *  
 * <p>
 * 示例：
 * <p>
 * MyLinkedList linkedList = new MyLinkedList();
 * linkedList.addAtHead(1);
 * linkedList.addAtTail(3);
 * linkedList.addAtIndex(1,2);   //链表变为1-> 2-> 3
 * linkedList.get(1);            //返回2
 * linkedList.deleteAtIndex(1);  //现在链表是1-> 3
 * linkedList.get(1);            //返回3
 *  
 * <p>
 * 提示：
 * <p>
 * 所有val值都在 [1, 1000] 之内。
 * 操作次数将在  [1, 1000] 之内。
 * 请不要使用内置的 LinkedList 库。
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/design-linked-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MyLinkedList {
    static class ListNode {
        int val;
        ListNode pre;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    //按双端队列实现
    private int size;

    //头尾节点，dummy节点
    private ListNode head;
    private ListNode tail;


    /**
     * Initialize your data structure here.
     */
    public MyLinkedList() {
        this.size = 0;
        //dummy node
        this.head = new ListNode(0);
        this.tail = new ListNode(0);
        head.next = tail;
        tail.pre = head;
    }

    /**
     * Get the value of the index-th node in the linked list. If the index is invalid, return -1.
     */
    public int get(int index) {
        if (index >= size) {
            return -1;
        }
        //判断从哪个方向扫描更快
        ListNode cur;
        if (index < size / 2) {
            cur = head;
            for (int i = 0; i <= index; i++) {
                cur = cur.next;
            }
        } else {
            //反向扫描
            cur = tail;
            for (int i = 0; i <= size - index - 1; i++) {
                cur = cur.pre;
            }
        }
        return cur.val;
    }

    /**
     * Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
     */
    public void addAtHead(int val) {
        ListNode node = new ListNode(val);
        ListNode next = head.next;
        node.next = next;
        next.pre = node;
        head.next = node;
        node.pre = head;
        size++;
    }

    /**
     * Append a node of value val to the last element of the linked list.
     */
    public void addAtTail(int val) {
        ListNode node = new ListNode(val);
        ListNode pre = tail.pre;
        node.pre = pre;
        pre.next = node;

        tail.pre = node;
        node.next = tail;
        size++;
    }

    /**
     * Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted.
     */
    public void addAtIndex(int index, int val) {
        if (index > size) {
            return;
        }
        ListNode cur;
        if (index == size) {
            addAtTail(val);
            return;
        }
        if (index < size / 2) {
            //顺序扫描
            cur = head;
            for (int i = 0; i < index; i++) {
                cur = cur.next;
            }
            ListNode next = cur.next;
            ListNode node = new ListNode(val);
            cur.next = node;
            node.pre = cur;

            node.next = next;
            next.pre = node;
        } else {
            //反向扫描
            cur = tail;
            for (int i = 0; i <= size - index - 1; i++) {
                cur = cur.pre;
            }
            ListNode pre = cur.pre;
            ListNode node = new ListNode(val);
            pre.next = node;
            node.pre = pre;

            node.next = cur;
            cur.pre = node;
        }
        size++;
    }

    /**
     * Delete the index-th node in the linked list, if the index is valid.
     */
    public void deleteAtIndex(int index) {
        if (index >= size) {
            return;
        }
        ListNode cur;
        if (index < size / 2) {
            //顺序扫描
            cur = head;
            for (int i = 0; i < index; i++) {
                cur = cur.next;
            }
            ListNode node = cur.next;
            ListNode next = node.next;

            cur.next = next;
            next.pre = cur;
            node.pre = null;
            node.next = null;
        } else {
            //反向扫描
            cur = tail;
            for (int i = 0; i < size - index - 1; i++) {
                cur = cur.pre;
            }
            ListNode node = cur.pre;
            ListNode pre = node.pre;

            pre.next = cur;
            cur.pre = pre;
            node.pre = null;
            node.next = null;
        }
        size--;
    }
}
