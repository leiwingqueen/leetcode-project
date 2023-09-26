package com.liyongquan.design;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class LFUCacheTest {

    /**
     * * // cnt(x) = 键 x 的使用计数
     * * // cache=[] 将显示最后一次使用的顺序（最左边的元素是最近的）
     * * LFUCache lFUCache = new LFUCache(2);
     * * lFUCache.put(1, 1);   // cache=[1,_], cnt(1)=1
     * * lFUCache.put(2, 2);   // cache=[2,1], cnt(2)=1, cnt(1)=1
     * * lFUCache.get(1);      // 返回 1
     * * // cache=[1,2], cnt(2)=1, cnt(1)=2
     * * lFUCache.put(3, 3);   // 去除键 2 ，因为 cnt(2)=1 ，使用计数最小
     * * // cache=[3,1], cnt(3)=1, cnt(1)=2
     * * lFUCache.get(2);      // 返回 -1（未找到）
     * * lFUCache.get(3);      // 返回 3
     * * // cache=[3,1], cnt(3)=2, cnt(1)=2
     * * lFUCache.put(4, 4);   // 去除键 1 ，1 和 3 的 cnt 相同，但 1 最久未使用
     * * // cache=[4,3], cnt(4)=1, cnt(3)=2
     * * lFUCache.get(1);      // 返回 -1（未找到）
     * * lFUCache.get(3);      // 返回 3
     * * // cache=[3,4], cnt(4)=1, cnt(3)=3
     * * lFUCache.get(4);      // 返回 4
     * * // cache=[3,4], cnt(4)=2, cnt(3)=3
     */
    @Test
    public void test() {
        LFUCache cache = new LFUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);
        cache.put(3, 3);
        int res = cache.get(2);
        log.info("{}", res);
        Assert.assertEquals(-1, res);
    }

    /**
     * ["LFUCache","put","put","put","put","get"]
     * [[2],[3,1],[2,1],[2,2],[4,4],[2]]
     */
    @Test
    public void test2() {
        LFUCache cache = new LFUCache(2);
        cache.put(3, 1);
        cache.put(2, 1);
        cache.put(2, 2);
        cache.put(4, 4);
        int res = cache.get(2);
        log.info("{}", res);
        Assert.assertEquals(2, res);
    }

    /**
     * ["LFUCache","put","put","get","get","put","get","get","get"]
     * [[2],[2,1],[3,2],[3],[2],[4,3],[2],[3],[4]]
     */
    @Test
    public void test3() {
        LFUCache cache = new LFUCache(2);
        cache.put(2, 1);
        cache.put(3, 2);
        cache.get(3);
        cache.get(2);
        cache.put(4, 3);
        cache.get(2);
        int res = cache.get(3);
        log.info("{}", res);
        Assert.assertEquals(-1, res);
    }


    // ["LFUCache","put","put","get","put","get","get","put","get","get","get"]
    // [[2],[1,1],[2,2],[1],[3,3],[2],[3],[4,4],[1],[3],[4]]
    // [null,null,null,1,null,-1,3,null,-1,3,4]
    @Test
    public void test4() {
        LFUCache cache = new LFUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        int res = cache.get(1);
        log.info("{}", res);
        Assert.assertEquals(1, res);
        cache.put(3, 3);
        res = cache.get(2);
        log.info("{}", res);
        Assert.assertEquals(-1, res);
        res = cache.get(3);
        log.info("{}", res);
        Assert.assertEquals(3, res);
        cache.put(4, 4);
        res = cache.get(1);
        log.info("{}", res);
        Assert.assertEquals(-1, res);
        res = cache.get(3);
        log.info("{}", res);
        Assert.assertEquals(3, res);
        res = cache.get(4);
        log.info("{}", res);
        Assert.assertEquals(4, res);
    }
}