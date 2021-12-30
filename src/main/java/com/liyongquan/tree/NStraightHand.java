package com.liyongquan.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

//Alice 手中有一把牌，她想要重新排列这些牌，分成若干组，使每一组的牌数都是 groupSize ，并且由 groupSize 张连续的牌组成。
//
//给你一个整数数组 hand 其中 hand[i] 是写在第 i 张牌，和一个整数 groupSize 。如果她可能重新排列这些牌，返回 true ；否则，返回 false 。
//
// 
//
//示例 1：
//
//输入：hand = [1,2,3,6,2,3,4,7,8], groupSize = 3
//输出：true
//解释：Alice 手中的牌可以被重新排列为 [1,2,3]，[2,3,4]，[6,7,8]。
//示例 2：
//
//输入：hand = [1,2,3,4,5], groupSize = 4
//输出：false
//解释：Alice 手中的牌无法被重新排列成几个大小为 4 的组。
// 
//
//提示：
//
//1 <= hand.length <= 104
//0 <= hand[i] <= 109
//1 <= groupSize <= hand.length
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/hand-of-straights
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
public class NStraightHand {
    public boolean isNStraightHand(int[] hand, int groupSize) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int h : hand) {
            map.put(h, map.getOrDefault(h, 0) + 1);
        }
        while (!map.isEmpty()) {
            int cnt = 0;
            int pre = -1;
            List<Integer> del = new ArrayList<>(groupSize);
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                if (pre < 0 || entry.getKey() == pre + 1) {
                    pre = entry.getKey();
                    del.add(entry.getKey());
                    cnt++;
                    if (cnt == groupSize) {
                        break;
                    }
                } else {
                    return false;
                }
            }
            if (cnt < groupSize) {
                return false;
            }
            //更新map
            for (Integer d : del) {
                if (map.get(d) == 1) {
                    map.remove(d);
                } else {
                    map.put(d, map.get(d) - 1);
                }
            }
        }
        return true;
    }

    /**
     * 时间复杂度O(nlogn)
     * <p>
     * 在上面的写法上做些优化
     *
     * @param hand
     * @param groupSize
     * @return
     */
    public boolean isNStraightHand2(int[] hand, int groupSize) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int h : hand) {
            map.put(h, map.getOrDefault(h, 0) + 1);
        }
        while (!map.isEmpty()) {
            Integer first = map.firstKey();
            int last = first + groupSize - 1;
            for (int i = first; i <= last; i++) {
                Integer cnt = map.getOrDefault(i, 0);
                if (cnt == 0) {
                    return false;
                }
                if (cnt == 1) {
                    map.remove(i);
                } else {
                    map.put(i, cnt - 1);
                }
            }
        }
        return true;
    }
}
