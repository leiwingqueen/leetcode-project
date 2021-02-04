package com.liyongquan.design;

import com.sun.xml.internal.bind.v2.model.core.ID;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * 705. 设计哈希集合
 * <p>
 * 不使用任何内建的哈希表库设计一个哈希集合（HashSet）。
 * <p>
 * 实现 MyHashSet 类：
 * <p>
 * void add(key) 向哈希集合中插入值 key 。
 * bool contains(key) 返回哈希集合中是否存在这个值 key 。
 * void remove(key) 将给定值 key 从哈希集合中删除。如果哈希集合中没有这个值，什么也不做。
 *  
 * 示例：
 * <p>
 * 输入：
 * ["MyHashSet", "add", "add", "contains", "contains", "add", "contains", "remove", "contains"]
 * [[], [1], [2], [1], [3], [2], [2], [2], [2]]
 * 输出：
 * [null, null, null, true, false, null, true, null, false]
 * <p>
 * 解释：
 * MyHashSet myHashSet = new MyHashSet();
 * myHashSet.add(1);      // set = [1]
 * myHashSet.add(2);      // set = [1, 2]
 * myHashSet.contains(1); // 返回 True
 * myHashSet.contains(3); // 返回 False ，（未找到）
 * myHashSet.add(2);      // set = [1, 2]
 * myHashSet.contains(2); // 返回 True
 * myHashSet.remove(2);   // set = [1]
 * myHashSet.contains(2); // 返回 False ，（已移除）
 *  
 * <p>
 * 提示：
 * <p>
 * 0 <= key <= 106
 * 最多调用 104 次 add、remove 和 contains 。
 *  
 * <p>
 * 进阶：你可以不使用内建的哈希集合库解决此问题吗？
 * <p>
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/design-hashset
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MyHashSet {
    public static final int DEFAULT_CAPACITY = 1000;
    /**
     * Initialize your data structure here.
     */
    //跟hashmap的设计类似，数组+冲突list
    private List<Integer>[] data;

    private int capacity;

    public MyHashSet() {
        this.capacity = DEFAULT_CAPACITY;
        data = new List[capacity];
    }

    public void add(int key) {
        if (contains(key)) {
            return;
        }
        int idx = getIdx(key);
        if (data[idx] == null) {
            data[idx] = new ArrayList<>();
        }
        data[idx].add(key);
    }

    public void remove(int key) {
        int[] index = index(key);
        if (index[0] < 0 || index[1] < 0) {
            return;
        }
        data[index[0]].remove(index[1]);
    }

    /**
     * Returns true if this set contains the specified element
     */
    public boolean contains(int key) {
        int[] index = index(key);
        return index[0] >= 0 && index[1] >= 0;
    }

    private int[] index(int key) {
        int idx = getIdx(key);
        if (data[idx] == null || data[idx].size() == 0) {
            return new int[]{-1, -1};
        }
        //遍历一次list
        for (int i = 0; i < data[idx].size(); i++) {
            Integer d = data[idx].get(i);
            if (d.intValue() == key) {
                return new int[]{idx, i};
            }
        }
        return new int[]{idx, -1};
    }

    private int getIdx(int key) {
        return key % capacity;
    }
}
