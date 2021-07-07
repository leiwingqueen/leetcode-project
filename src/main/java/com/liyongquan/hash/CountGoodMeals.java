package com.liyongquan.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * 大餐 是指 恰好包含两道不同餐品 的一餐，其美味程度之和等于 2 的幂。
 * <p>
 * 你可以搭配 任意 两道餐品做一顿大餐。
 * <p>
 * 给你一个整数数组 deliciousness ，其中 deliciousness[i] 是第 i​​​​​​​​​​​​​​ 道餐品的美味程度，返回你可以用数组中的餐品做出的不同 大餐 的数量。结果需要对 109 + 7 取余。
 * <p>
 * 注意，只要餐品下标不同，就可以认为是不同的餐品，即便它们的美味程度相同。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：deliciousness = [1,3,5,7,9]
 * 输出：4
 * 解释：大餐的美味程度组合为 (1,3) 、(1,7) 、(3,5) 和 (7,9) 。
 * 它们各自的美味程度之和分别为 4 、8 、8 和 16 ，都是 2 的幂。
 * 示例 2：
 * <p>
 * 输入：deliciousness = [1,1,1,3,3,3,7]
 * 输出：15
 * 解释：大餐的美味程度组合为 3 种 (1,1) ，9 种 (1,3) ，和 3 种 (1,7) 。
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= deliciousness.length <= 105
 * 0 <= deliciousness[i] <= 220
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-good-meals
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CountGoodMeals {
    public int countPairs(int[] deliciousness) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int del : deliciousness) {
            map.put(del, map.getOrDefault(del, 0) + 1);
        }
        int cnt = 0;
        int mod = 1000000007;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            Integer n1 = entry.getKey();
            Integer c1 = entry.getValue();
            for (int i = 1; i <= 21; i++) {
                int sum = (int) Math.pow(2, i);
                int n2 = sum - n1;
                int c2 = map.getOrDefault(n2, 0);
                if (n1 == n2) {
                    c2--;
                }
                int inc = (int) ((long) c1 * c2) % mod;
                cnt = (cnt + inc) % mod;
            }
        }
        //组合会重复，需要/2
        return cnt / 2;
    }
}
