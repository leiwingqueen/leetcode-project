package com.liyongquan.design;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class AnimalShelfTest {
    /**
     * ["AnimalShelf", "enqueue", "enqueue", "dequeueCat", "dequeueDog", "dequeueAny"]
     * [[], [[0, 0]], [[1, 0]], [], [], []]
     * 输出：
     * [null,null,null,[0,0],[-1,-1],[1,0]]
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/animal-shelter-lcci
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    @Test
    public void test1() {
        AnimalShelf as = new AnimalShelf();
        as.enqueue(new int[]{0, 0});
        as.enqueue(new int[]{1, 0});
        int[] de = as.dequeueCat();
        assertArrayEquals(new int[]{0, 0}, de);
        de = as.dequeueDog();
        assertArrayEquals(new int[]{-1, -1}, de);
        de = as.dequeueAny();
        assertArrayEquals(new int[]{1, 0}, de);
    }

    @Test
    public void test2() {
        AnimalShelf as = new AnimalShelf();
        as.enqueue(new int[]{0, 0});
        as.enqueue(new int[]{1, 0});
        as.enqueue(new int[]{2, 1});
        as.enqueue(new int[]{3, 1});
        as.enqueue(new int[]{4, 0});
        int[] de = as.dequeueDog();
        assertArrayEquals(new int[]{2, 1}, de);
    }
}