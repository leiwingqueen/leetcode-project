package com.liyongquan.binarySort;

/**
 * //传送带上的包裹必须在 D 天内从一个港口运送到另一个港口。
 * //
 * // 传送带上的第 i 个包裹的重量为 weights[i]。每一天，我们都会按给出重量的顺序往传送带上装载包裹。我们装载的重量不会超过船的最大运载重量。
 * //
 * // 返回能在 D 天内将传送带上的所有包裹送达的船的最低运载能力。
 * //
 * //
 * //
 * // 示例 1：
 * //
 * // 输入：weights = [1,2,3,4,5,6,7,8,9,10], D = 5
 * //输出：15
 * //解释：
 * //船舶最低载重 15 就能够在 5 天内送达所有包裹，如下所示：
 * //第 1 天：1, 2, 3, 4, 5
 * //第 2 天：6, 7
 * //第 3 天：8
 * //第 4 天：9
 * //第 5 天：10
 * //
 * //请注意，货物必须按照给定的顺序装运，因此使用载重能力为 14 的船舶并将包装分成 (2, 3, 4, 5), (1, 6, 7), (8), (9), (1
 * //0) 是不允许的。
 * //
 * //
 * // 示例 2：
 * //
 * // 输入：weights = [3,2,2,4,1,4], D = 3
 * //输出：6
 * //解释：
 * //船舶最低载重 6 就能够在 3 天内送达所有包裹，如下所示：
 * //第 1 天：3, 2
 * //第 2 天：2, 4
 * //第 3 天：1, 4
 * //
 * //
 * // 示例 3：
 * //
 * // 输入：weights = [1,2,3,1,1], D = 4
 * //输出：3
 * //解释：
 * //第 1 天：1
 * //第 2 天：2
 * //第 3 天：3
 * //第 4 天：1, 1
 * //
 * //
 * //
 * //
 * // 提示：
 * //
 * //
 * // 1 <= D <= weights.length <= 50000
 * // 1 <= weights[i] <= 500
 * //
 * // Related Topics 数组 二分查找
 * // 👍 246 👎 0
 */
public class ShipWithinDays {
    /**
     * 没有什么特别好的思路，直接二分
     * <p>
     * 时间复杂度O(n*log(sum(weights)))
     *
     * @param weights
     * @param D
     * @return
     */
    public int shipWithinDays(int[] weights, int D) {
        int len = weights.length;
        int max = 0, sum = 0;
        for (int i = 0; i < len; i++) {
            max = Math.max(max, weights[i]);
            sum += weights[i];
        }
        //求解的范围[max,sum]
        if (D == len) {
            return max;
        }
        if (D == 1) {
            return sum;
        }
        int l = max, r = sum;
        while (l < r) {
            int mid = l + (r - l) / 2;
            int day = shipDay(weights, mid);
            if (day > D) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }

    private int shipDay(int[] weights, int k) {
        int cnt = 1;
        int sum = 0;
        for (int i = 0; i < weights.length; i++) {
            sum += weights[i];
            if (sum > k) {
                cnt++;
                sum = weights[i];
            }
        }
        return cnt;
    }
}
