package com.liyongquan.greedy;

import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个偶数长度的数组，其中不同的数字代表着不同种类的糖果，每一个数字代表一个糖果。你需要把这些糖果平均分给一个弟弟和一个妹妹。返回妹妹可以获得的最大糖果的种类数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: candies = [1,1,2,2,3,3]
 * 输出: 3
 * 解析: 一共有三种种类的糖果，每一种都有两个。
 * 最优分配方案：妹妹获得[1,2,3],弟弟也获得[1,2,3]。这样使妹妹获得糖果的种类数最多。
 * 示例 2 :
 * <p>
 * 输入: candies = [1,1,2,3]
 * 输出: 2
 * 解析: 妹妹获得糖果[2,3],弟弟获得糖果[1,1]，妹妹有两种不同的糖果，弟弟只有一种。这样使得妹妹可以获得的糖果种类数最多。
 * 注意:
 * <p>
 * 数组的长度为[2, 10,000]，并且确定为偶数。
 * 数组中数字的大小在范围[-100,000, 100,000]内。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/distribute-candies
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class DistributeCandies {
    /**
     * bitmap解法，常数级复杂度
     *
     * @param candies
     * @return
     */
    public int distributeCandies(int[] candies) {
        int[] bitmap = new int[200001];
        for (int i = 0; i < candies.length; i++) {
            int pos = candies[i] + 100000;
            bitmap[pos]++;
        }
        int i = 0, c = 0, count = 0;
        while (i < bitmap.length && c < candies.length / 2) {
            if (bitmap[i] > 0) {
                count++;
                c++;
            }
            i++;
        }
        return count;
    }

    /**
     * 解法2，改为set
     *
     * @param candies
     * @return
     */
    public int distributeCandies2(int[] candies) {
        Set<Integer> set = new HashSet<>(candies.length);
        for (int i = 0; i < candies.length; i++) {
            set.add(candies[i]);
        }
        return Math.min(set.size(), candies.length / 2);
    }

    public static void main(String[] args) {
        DistributeCandies candies = new DistributeCandies();
        int i = candies.distributeCandies(new int[]{1, 1, 2, 2, 3, 3});
        System.out.println(i);
    }
}
