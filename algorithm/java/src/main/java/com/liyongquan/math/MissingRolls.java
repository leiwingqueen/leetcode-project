package com.liyongquan.math;

//2028. 找出缺失的观测数据
//现有一份 n + m 次投掷单个 六面 骰子的观测数据，骰子的每个面从 1 到 6 编号。观测数据中缺失了 n 份，你手上只拿到剩余 m 次投掷的数据。幸好你有之前计算过的这 n + m 次投掷数据的 平均值 。
//
//给你一个长度为 m 的整数数组 rolls ，其中 rolls[i] 是第 i 次观测的值。同时给你两个整数 mean 和 n 。
//
//返回一个长度为 n 的数组，包含所有缺失的观测数据，且满足这 n + m 次投掷的 平均值 是 mean 。如果存在多组符合要求的答案，只需要返回其中任意一组即可。如果不存在答案，返回一个空数组。
//
//k 个数字的 平均值 为这些数字求和后再除以 k 。
//
//注意 mean 是一个整数，所以 n + m 次投掷的总和需要被 n + m 整除。
//
// 
//
//示例 1：
//
//输入：rolls = [3,2,4,3], mean = 4, n = 2
//输出：[6,6]
//解释：所有 n + m 次投掷的平均值是 (3 + 2 + 4 + 3 + 6 + 6) / 6 = 4 。
//示例 2：
//
//输入：rolls = [1,5,6], mean = 3, n = 4
//输出：[2,3,2,2]
//解释：所有 n + m 次投掷的平均值是 (1 + 5 + 6 + 2 + 3 + 2 + 2) / 7 = 3 。
//示例 3：
//
//输入：rolls = [1,2,3,4], mean = 6, n = 4
//输出：[]
//解释：无论丢失的 4 次数据是什么，平均值都不可能是 6 。
//示例 4：
//
//输入：rolls = [1], mean = 3, n = 1
//输出：[5]
//解释：所有 n + m 次投掷的平均值是 (1 + 5) / 2 = 3 。
// 
//
//提示：
//
//m == rolls.length
//1 <= n, m <= 105
//1 <= rolls[i], mean <= 6
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/find-missing-observations
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

public class MissingRolls {
    public int[] missingRolls(int[] rolls, int mean, int n) {
        int m = rolls.length;
        int total = (m + n) * mean;
        int sum = 0;
        for (int roll : rolls) {
            sum += roll;
        }
        int s = total - sum;
        if (s < n || s > 6 * n) {
            return new int[]{};
        }
        int idx = 0;
        int[] res = new int[n];
        while (idx < n) {
            //k=n-idx-1,k<=s<=6*k
            //选择后的总数的最小值和最大值
            int k = n - idx - 1;
            for (int i = 1; i <= 6; i++) {
                if (s - i >= k && s - i <= 6 * k) {
                    s -= i;
                    res[idx++] = i;
                    break;
                }
            }
        }
        return res;
    }
}
