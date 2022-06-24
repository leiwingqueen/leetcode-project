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
 * bst解法，getMax key大概是O(logn)的时间复杂度
 */
public class AllOne {
    Map<String, Integer> mp;
    TreeMap<Integer, Set<String>> mp2;

    public AllOne() {
        mp = new HashMap<>();
        mp2 = new TreeMap<>();
    }

    public void inc(String key) {
        if (!mp.containsKey(key)) {
            mp.put(key, 0);
        }
        Integer old = mp.get(key);
        int nw = old + 1;
        mp.put(key, nw);
        if (old > 0) {
            mp2.get(old).remove(key);
            if (mp2.get(old).size() == 0) {
                mp2.remove(old);
            }
        }
        if (!mp2.containsKey(nw)) {
            mp2.put(nw, new HashSet<>());
        }
        mp2.get(nw).add(key);
    }

    public void dec(String key) {
        Integer old = mp.get(key);
        int nw = old - 1;
        mp.put(key, nw);
        mp2.get(old).remove(key);
        if (mp2.get(old).size() == 0) {
            mp2.remove(old);
        }
        if (nw > 0) {
            if (!mp2.containsKey(nw)) {
                mp2.put(nw, new HashSet<>());
            }
            mp2.get(nw).add(key);
        }
    }

    /**
     * 时间复杂度O(logn)
     *
     * @return
     */
    public String getMaxKey() {
        Map.Entry<Integer, Set<String>> entry = mp2.lastEntry();
        if (entry == null) {
            return "";
        }
        return entry.getValue().stream().findFirst().get();
    }

    public String getMinKey() {
        Map.Entry<Integer, Set<String>> entry = mp2.firstEntry();
        if (entry == null) {
            return "";
        }
        return entry.getValue().stream().findFirst().get();
    }
}
