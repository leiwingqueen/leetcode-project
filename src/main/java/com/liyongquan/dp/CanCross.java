package com.liyongquan.dp;


import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 一只青蛙想要过河。 假定河流被等分为若干个单元格，并且在每一个单元格内都有可能放有一块石子（也有可能没有）。 青蛙可以跳上石子，但是不可以跳入水中。
 * <p>
 * 给你石子的位置列表 stones（用单元格序号 升序 表示）， 请判定青蛙能否成功过河（即能否在最后一步跳至最后一块石子上）。
 * <p>
 * 开始时， 青蛙默认已站在第一块石子上，并可以假定它第一步只能跳跃一个单位（即只能从单元格 1 跳至单元格 2 ）。
 * <p>
 * 如果青蛙上一步跳跃了 k 个单位，那么它接下来的跳跃距离只能选择为 k - 1、k 或 k + 1 个单位。 另请注意，青蛙只能向前方（终点的方向）跳跃。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：stones = [0,1,3,5,6,8,12,17]
 * 输出：true
 * 解释：青蛙可以成功过河，按照如下方案跳跃：跳 1 个单位到第 2 块石子, 然后跳 2 个单位到第 3 块石子, 接着 跳 2 个单位到第 4 块石子, 然后跳 3 个单位到第 6 块石子, 跳 4 个单位到第 7 块石子, 最后，跳 5 个单位到第 8 个石子（即最后一块石子）。
 * 示例 2：
 * <p>
 * 输入：stones = [0,1,2,3,4,8,9,11]
 * 输出：false
 * 解释：这是因为第 5 和第 6 个石子之间的间距太大，没有可选的方案供青蛙跳跃过去。
 *  
 * <p>
 * 提示：
 * <p>
 * 2 <= stones.length <= 2000
 * 0 <= stones[i] <= 231 - 1
 * stones[0] == 0
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/frog-jump
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
@Slf4j
public class CanCross {
    /**
     * 先尝试一个最简单的回溯
     * <p>
     * 超时
     * <p>
     * 时间复杂度O(3^n)
     * <p>
     * 增加了记忆，勉强能过
     *
     * @param stones
     * @return
     */
    public boolean canCross(int[] stones) {
        Set<Integer> set = new HashSet<>(stones.length);
        for (int stone : stones) {
            set.add(stone);
        }
        Map<String, Boolean> map = new HashMap<>();
        return backtrace(set, 0, stones[stones.length - 1], 0, map);
    }

    private boolean backtrace(Set<Integer> stones, int cur, int target, int step, Map<String, Boolean> map) {
        if (cur == target) {
            return true;
        }
        if (cur > target) {
            return false;
        }
        int[] dis = {step - 1, step, step + 1};
        for (int d : dis) {
            if (d > 0) {
                if (stones.contains(cur + d)) {
                    String key = String.format("%s_%s", cur + d, d);
                    boolean res = false;
                    if (map.containsKey(key)) {
                        map.get(key);
                    } else {
                        res = backtrace(stones, cur + d, target, d, map);
                        map.put(key, res);
                    }
                    if (res) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
