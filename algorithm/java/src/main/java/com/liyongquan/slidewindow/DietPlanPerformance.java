package com.liyongquan.slidewindow;

/**
 * 你的好友是一位健身爱好者。前段日子，他给自己制定了一份健身计划。现在想请你帮他评估一下这份计划是否合理。
 * <p>
 * 他会有一份计划消耗的卡路里表，其中 calories[i] 给出了你的这位好友在第 i 天需要消耗的卡路里总量。
 * <p>
 * 为了更好地评估这份计划，对于卡路里表中的每一天，你都需要计算他 「这一天以及之后的连续几天」 （共 k 天）内消耗的总卡路里 T：
 * <p>
 * 如果 T < lower，那么这份计划相对糟糕，并失去 1 分； 
 * 如果 T > upper，那么这份计划相对优秀，并获得 1 分；
 * 否则，这份计划普普通通，分值不做变动。
 * 请返回统计完所有 calories.length 天后得到的总分作为评估结果。
 * <p>
 * 注意：总分可能是负数。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：calories = [1,2,3,4,5], k = 1, lower = 3, upper = 3
 * 输出：0
 * 解释：calories[0], calories[1] < lower 而 calories[3], calories[4] > upper, 总分 = 0.
 * 示例 2：
 * <p>
 * 输入：calories = [3,2], k = 2, lower = 0, upper = 1
 * 输出：1
 * 解释：calories[0] + calories[1] > upper, 总分 = 1.
 * 示例 3：
 * <p>
 * 输入：calories = [6,5,0,0], k = 2, lower = 1, upper = 5
 * 输出：0
 * 解释：calories[0] + calories[1] > upper, calories[2] + calories[3] < lower, 总分 = 0.
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= k <= calories.length <= 10^5
 * 0 <= calories[i] <= 20000
 * 0 <= lower <= upper
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/diet-plan-performance
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class DietPlanPerformance {
    /**
     * 没什么好说的，题意本身就是滑动窗口
     *
     * @param calories
     * @param k
     * @param lower
     * @param upper
     * @return
     */
    public int dietPlanPerformance(int[] calories, int k, int lower, int upper) {
        //初始化
        int sum = 0, point = 0;
        for (int i = 0; i < k; i++) {
            sum += calories[i];
        }
        if (sum > upper) {
            point++;
        } else if (sum < lower) {
            point--;
        }
        //滑动窗口
        for (int i = 1; i <= calories.length - k; i++) {
            sum += calories[i + k - 1] - calories[i - 1];
            if (sum > upper) {
                point++;
            } else if (sum < lower) {
                point--;
            }
        }
        return point;
    }
}
