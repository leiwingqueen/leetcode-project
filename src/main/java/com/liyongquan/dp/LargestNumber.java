package com.liyongquan.dp;

import java.math.BigDecimal;

/**
 * 1449. 数位成本和为目标值的最大数字
 * <p>
 * 给你一个整数数组 cost 和一个整数 target 。请你返回满足如下规则可以得到的 最大 整数：
 * <p>
 * 给当前结果添加一个数位（i + 1）的成本为 cost[i] （cost 数组下标从 0 开始）。
 * 总成本必须恰好等于 target 。
 * 添加的数位中没有数字 0 。
 * 由于答案可能会很大，请你以字符串形式返回。
 * <p>
 * 如果按照上述要求无法得到任何整数，请你返回 "0" 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：cost = [4,3,2,5,6,7,2,5,5], target = 9
 * 输出："7772"
 * 解释：添加数位 '7' 的成本为 2 ，添加数位 '2' 的成本为 3 。所以 "7772" 的代价为 2*3+ 3*1 = 9 。 "977" 也是满足要求的数字，但 "7772" 是较大的数字。
 * 数字     成本
 * 1  ->   4
 * 2  ->   3
 * 3  ->   2
 * 4  ->   5
 * 5  ->   6
 * 6  ->   7
 * 7  ->   2
 * 8  ->   5
 * 9  ->   5
 * 示例 2：
 * <p>
 * 输入：cost = [7,6,5,5,5,6,8,7,8], target = 12
 * 输出："85"
 * 解释：添加数位 '8' 的成本是 7 ，添加数位 '5' 的成本是 5 。"85" 的成本为 7 + 5 = 12 。
 * 示例 3：
 * <p>
 * 输入：cost = [2,4,6,2,4,6,4,4,4], target = 5
 * 输出："0"
 * 解释：总成本是 target 的条件下，无法生成任何整数。
 * 示例 4：
 * <p>
 * 输入：cost = [6,10,15,40,40,40,40,40,40], target = 47
 * 输出："32211"
 *  
 * <p>
 * 提示：
 * <p>
 * cost.length == 9
 * 1 <= cost[i] <= 5000
 * 1 <= target <= 5000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/form-largest-integer-with-digits-that-add-up-to-target
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LargestNumber {
    public String largestNumber(int[] cost, int target) {
        int min = Integer.MAX_VALUE;
        for (int c : cost) {
            min = Math.min(min, c);
        }
        //最大的数字长度，决定解的范围
        int maxLen = target / min;
        if (maxLen <= 0) {
            return "0";
        }
        //初始化
        BigDecimal[] pre = new BigDecimal[target + 1], cur = new BigDecimal[target + 1];
        for (int j = 0; j <= target; j++) {
            for (int k = 0; k < 9; k++) {
                if (cost[k] == j) {
                    if (pre[j] == null || BigDecimal.valueOf(k + 1).compareTo(pre[j]) > 0) {
                        pre[j] = BigDecimal.valueOf(k + 1);
                    }
                    //pre[j] = Math.max(pre[j], k + 1);
                }
            }
        }
        BigDecimal res = null;
        for (int i = 1; i < maxLen; i++) {
            for (int j = 0; j <= target; j++) {
                for (int k = 0; k < 9; k++) {
                    if (j - cost[k] >= 0 && pre[j - cost[k]] != null) {
                        BigDecimal r = pre[j - cost[k]].multiply(BigDecimal.TEN).add(BigDecimal.valueOf(k + 1));
                        if (cur[j] == null || r.compareTo(cur[j]) > 0) {
                            cur[j] = r;
                        }
                    }
                }
            }
            pre = cur;
            cur = new BigDecimal[target + 1];
            //取最大的合法解
            if (res == null || (pre[target] != null && pre[target].compareTo(res) > 0)) {
                res = pre[target];
            }
        }
        return res == null ? "0" : res.toString();
    }
}
