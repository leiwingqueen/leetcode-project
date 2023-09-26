package com.liyongquan.design;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * 460. LFU 缓存
 * <p>
 * 请你为 最不经常使用（LFU）缓存算法设计并实现数据结构。
 * <p>
 * 实现 LFUCache 类：
 * <p>
 * LFUCache(int capacity) - 用数据结构的容量 capacity 初始化对象
 * int get(int key) - 如果键存在于缓存中，则获取键的值，否则返回 -1。
 * void put(int key, int value) - 如果键已存在，则变更其值；如果键不存在，请插入键值对。当缓存达到其容量时，则应该在插入新项之前，使最不经常使用的项无效。在此问题中，当存在平局（即两个或更多个键具有相同使用频率）时，应该去除 最久未使用 的键。
 * 注意「项的使用次数」就是自插入该项以来对其调用 get 和 put 函数的次数之和。使用次数会在对应项被移除后置为 0 。
 * <p>
 * 为了确定最不常使用的键，可以为缓存中的每个键维护一个 使用计数器 。使用计数最小的键是最久未使用的键。
 * <p>
 * 当一个键首次插入到缓存中时，它的使用计数器被设置为 1 (由于 put 操作)。对缓存中的键执行 get 或 put 操作，使用计数器的值将会递增。
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * 输入：
 * ["LFUCache", "put", "put", "get", "put", "get", "get", "put", "get", "get", "get"]
 * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [3], [4, 4], [1], [3], [4]]
 * 输出：
 * [null, null, null, 1, null, -1, 3, null, -1, 3, 4]
 * <p>
 * 解释：
 * // cnt(x) = 键 x 的使用计数
 * // cache=[] 将显示最后一次使用的顺序（最左边的元素是最近的）
 * LFUCache lFUCache = new LFUCache(2);
 * lFUCache.put(1, 1);   // cache=[1,_], cnt(1)=1
 * lFUCache.put(2, 2);   // cache=[2,1], cnt(2)=1, cnt(1)=1
 * lFUCache.get(1);      // 返回 1
 * // cache=[1,2], cnt(2)=1, cnt(1)=2
 * lFUCache.put(3, 3);   // 去除键 2 ，因为 cnt(2)=1 ，使用计数最小
 * // cache=[3,1], cnt(3)=1, cnt(1)=2
 * lFUCache.get(2);      // 返回 -1（未找到）
 * lFUCache.get(3);      // 返回 3
 * // cache=[3,1], cnt(3)=2, cnt(1)=2
 * lFUCache.put(4, 4);   // 去除键 1 ，1 和 3 的 cnt 相同，但 1 最久未使用
 * // cache=[4,3], cnt(4)=1, cnt(3)=2
 * lFUCache.get(1);      // 返回 -1（未找到）
 * lFUCache.get(3);      // 返回 3
 * // cache=[3,4], cnt(4)=1, cnt(3)=3
 * lFUCache.get(4);      // 返回 4
 * // cache=[3,4], cnt(4)=2, cnt(3)=3
 *  
 * <p>
 * 提示：
 * <p>
 * 0 <= capacity, key, value <= 104
 * 最多调用 105 次 get 和 put 方法
 *  
 * <p>
 * 进阶：你可以为这两种操作设计时间复杂度为 O(1) 的实现吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/lfu-cache
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LFUCache {
    /**
     * 不通过，因为如果访问频数相同是以最后访问的时间排序，并不是以插入的时间顺序
     */
    // key-[cnt+time],value-key
    private TreeMap<Long, Integer> map1;
    private Map<Integer, CounterValue> map2;

    private int capacity;
    private int timestamp;

    public LFUCache(int capacity) {
        this.map1 = new TreeMap<>();
        this.map2 = new HashMap<>(capacity + 1);
        this.capacity = capacity;
        timestamp = 0;
    }

    /**
     * O(1)
     *
     * @param key
     * @return
     */
    public int get(int key) {
        if (!this.map2.containsKey(key)) {
            return -1;
        }
        CounterValue cv = map2.get(key);
        map1.remove(buildKey(cv.cnt, cv.time));
        timestamp++;
        cv.cnt += 1;
        cv.time = timestamp;
        map1.put(buildKey(cv.cnt, cv.time), key);
        return cv.value;
    }

    /**
     * nlog(n)，主要是需要构造小顶堆的成本
     *
     * @param key
     * @param value
     */
    public void put(int key, int value) {
        if (this.capacity == 0) {
            return;
        }
        // expire(key);
        CounterValue cv;
        timestamp++;
        if (this.map2.containsKey(key)) {
            cv = map2.get(key);
            map1.remove(buildKey(cv.cnt, cv.time));
            cv.value = value;
            cv.cnt += 1;
            cv.time = timestamp;
        } else {
            cv = new CounterValue(key, value, 1, timestamp);
            // 淘汰
            if (this.map2.size() >= this.capacity) {
                Map.Entry<Long, Integer> entry = map1.pollFirstEntry();
                map2.remove(entry.getValue());
            }
        }
        map2.put(key, cv);
        map1.put(buildKey(cv.cnt, cv.time), key);
    }

    private long buildKey(int cnt, int time) {
        return (((long) cnt) << 32) + time;
    }

    private static class CounterValue {
        int cnt;
        int time;
        int key;
        int value;

        public CounterValue(int key, int value, int cnt, int time) {
            this.key = key;
            this.time = time;
            this.value = value;
            this.cnt = cnt;
        }
    }
}
