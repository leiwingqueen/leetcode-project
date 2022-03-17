package com.liyongquan.design;

//请你设计一个用于存储字符串计数的数据结构，并能够返回计数最小和最大的字符串。
//
//实现 AllOne 类：
//
//AllOne() 初始化数据结构的对象。
//inc(String key) 字符串 key 的计数增加 1 。如果数据结构中尚不存在 key ，那么插入计数为 1 的 key 。
//dec(String key) 字符串 key 的计数减少 1 。如果 key 的计数在减少后为 0 ，那么需要将这个 key 从数据结构中删除。测试用例保证：在减少计数前，key 存在于数据结构中。
//getMaxKey() 返回任意一个计数最大的字符串。如果没有元素存在，返回一个空字符串 "" 。
//getMinKey() 返回任意一个计数最小的字符串。如果没有元素存在，返回一个空字符串 "" 。
// 
//
//示例：
//
//输入
//["AllOne", "inc", "inc", "getMaxKey", "getMinKey", "inc", "getMaxKey", "getMinKey"]
//[[], ["hello"], ["hello"], [], [], ["leet"], [], []]
//输出
//[null, null, null, "hello", "hello", null, "hello", "leet"]
//
//解释
//AllOne allOne = new AllOne();
//allOne.inc("hello");
//allOne.inc("hello");
//allOne.getMaxKey(); // 返回 "hello"
//allOne.getMinKey(); // 返回 "hello"
//allOne.inc("leet");
//allOne.getMaxKey(); // 返回 "hello"
//allOne.getMinKey(); // 返回 "leet"
// 
//
//提示：
//
//1 <= key.length <= 10
//key 由小写英文字母组成
//测试用例保证：在每次调用 dec 时，数据结构中总存在 key
//最多调用 inc、dec、getMaxKey 和 getMinKey 方法 5 * 104 次
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/all-oone-data-structure
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

import java.util.*;

/**
 * hashmap+双端队列
 * <p>
 * 双端队列维护一个根据次数递增链表，一个节点保存了同样次数的所有节点
 */
public class AllOne2 {
    Map<String, Node> mp;
    Node dummyHead;
    Node dummyTail;

    public AllOne2() {
        mp = new HashMap<>();
        dummyHead = new Node("", 0);
        dummyTail = new Node("", Integer.MAX_VALUE);
        dummyHead.next = dummyTail;
        dummyTail.prev = dummyHead;
    }

    public void inc(String key) {
        if (!mp.containsKey(key)) {
            //找到插入的位置
            Node node;
            if (dummyHead.next.cnt == 1) {
                node = dummyHead.next;
                node.add(key);
            } else {
                //插入结点
                node = new Node(key, 1);
                Node next = dummyHead.next;
                dummyHead.next = node;
                node.next = next;
                node.prev = dummyHead;
                next.prev = node;
            }
            mp.put(key, node);
        } else {
            Node node = mp.get(key);
            node.remove(key);
            if (node.next.cnt == node.cnt + 1) {
                //直接更新下一个节点
                node.next.add(key);
                mp.put(key, node.next);
            } else {
                //插入新节点
                Node nd = new Node(key, node.cnt + 1);
                Node next = node.next;
                node.next = nd;
                nd.next = next;
                nd.prev = node;
                next.prev = nd;
                mp.put(key, nd);
            }
            //删除节点
            if (node.valSet.size() == 0) {
                Node prev = node.prev;
                Node next = node.next;
                prev.next = next;
                next.prev = prev;
            }
        }
    }

    public void dec(String key) {
        Node node = mp.get(key);
        node.remove(key);
        if (node.prev.cnt == node.cnt - 1) {
            if (node.cnt > 1) {
                //直接更新上一个节点
                node.prev.add(key);
                mp.put(key, node.prev);
            } else {
                mp.remove(key);
            }
        } else {
            //插入新节点
            Node nd = new Node(key, node.cnt - 1);
            Node prev = node.prev;
            prev.next = nd;
            nd.next = node;
            node.prev = nd;
            nd.prev = prev;
            mp.put(key, nd);
        }
        //删除节点
        if (node.valSet.size() == 0) {
            Node prev = node.prev;
            Node next = node.next;
            prev.next = next;
            next.prev = prev;
        }
    }

    /**
     * 时间复杂度O(logn)
     *
     * @return
     */
    public String getMaxKey() {
        Node prev = dummyTail.prev;
        return prev.cnt > 0 ? prev.valSet.stream().findFirst().get() : "";
    }

    public String getMinKey() {
        Node next = dummyHead.next;
        return next.cnt > 0 ? next.valSet.stream().findFirst().get() : "";
    }

    private static class Node {
        Node prev;
        Node next;
        int cnt;
        Set<String> valSet;

        public Node(String v, int cnt) {
            this.valSet = new HashSet<>();
            this.valSet.add(v);
            this.cnt = cnt;
        }

        public void add(String v) {
            this.valSet.add(v);
        }

        public void remove(String v) {
            this.valSet.remove(v);
        }

    }
}
