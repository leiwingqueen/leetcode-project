package com.liyongquan.slidewindow;

import java.util.Arrays;

/**
 * 几张卡牌 排成一行，每张卡牌都有一个对应的点数。点数由整数数组 cardPoints 给出。
 * <p>
 * 每次行动，你可以从行的开头或者末尾拿一张卡牌，最终你必须正好拿 k 张卡牌。
 * <p>
 * 你的点数就是你拿到手中的所有卡牌的点数之和。
 * <p>
 * 给你一个整数数组 cardPoints 和整数 k，请你返回可以获得的最大点数。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：cardPoints = [1,2,3,4,5,6,1], k = 3
 * 输出：12
 * 解释：第一次行动，不管拿哪张牌，你的点数总是 1 。但是，先拿最右边的卡牌将会最大化你的可获得点数。最优策略是拿右边的三张牌，最终点数为 1 + 6 + 5 = 12 。
 * 示例 2：
 * <p>
 * 输入：cardPoints = [2,2,2], k = 2
 * 输出：4
 * 解释：无论你拿起哪两张卡牌，可获得的点数总是 4 。
 * 示例 3：
 * <p>
 * 输入：cardPoints = [9,7,7,9,7,7,9], k = 7
 * 输出：55
 * 解释：你必须拿起所有卡牌，可以获得的点数为所有卡牌的点数之和。
 * 示例 4：
 * <p>
 * 输入：cardPoints = [1,1000,1], k = 1
 * 输出：1
 * 解释：你无法拿到中间那张卡牌，所以可以获得的最大点数为 1 。
 * 示例 5：
 * <p>
 * 输入：cardPoints = [1,79,80,1,1,1,200,1], k = 3
 * 输出：202
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= cardPoints.length <= 10^5
 * 1 <= cardPoints[i] <= 10^4
 * 1 <= k <= cardPoints.length
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-points-you-can-obtain-from-cards
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MaxScore {
    /**
     * Let the sum of all points be total_pts. You need to remove a sub-array from cardPoints with length n - k.
     * Keep a window of size n - k over the array. The answer is max(answer, total_pts - sumOfCurrentWindow)
     * <p>
     * 这个想法妙啊，直接转换成滑动窗口了。
     * <p>
     * 时间复杂度O(2n)，空间复杂度O(1)
     *
     * @param cardPoints
     * @param k
     * @return
     */
    public int maxScore(int[] cardPoints, int k) {
        int len = cardPoints.length - k;
        //初始化第一个窗口
        int sum = 0, s = 0;
        for (int i = 0; i < cardPoints.length; i++) {
            if (i < len) {
                sum += cardPoints[i];
            }
            s += cardPoints[i];
        }
        int min = sum;
        //滑动窗口迭代
        for (int i = 1; i <= cardPoints.length - len; i++) {
            sum += (cardPoints[i + len - 1] - cardPoints[i - 1]);
            min = Math.min(min, sum);
        }
        return s - min;
    }

    //TODO:还有一种前缀和的解法，有时间可以也思考一下
}
