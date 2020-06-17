package com.liyongquan.dp;

/**
 * 给定正整数数组 A，A[i] 表示第 i 个观光景点的评分，并且两个景点 i 和 j 之间的距离为 j - i。
 * <p>
 * 一对景点（i < j）组成的观光组合的得分为（A[i] + A[j] + i - j）：景点的评分之和减去它们两者之间的距离。
 * <p>
 * 返回一对观光景点能取得的最高分。
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * 输入：[8,1,5,2,6]
 * 输出：11
 * 解释：i = 0, j = 2, A[i] + A[j] + i - j = 8 + 5 + 0 - 2 = 11
 *  
 * <p>
 * 提示：
 * <p>
 * 2 <= A.length <= 50000
 * 1 <= A[i] <= 1000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/best-sightseeing-pair
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Sightseeing {
    /**
     * 假设q(n)=max{A[0]+0,A[1]+1,..A[n]+n}
     * dp方程：
     * f(n)=max{f(n-1),q(n-1)+A[n-1]-(n-1)}
     * <p>
     * 初始化：
     * f(2)=A[0]+A[1]-1
     * q(2)=max{A[0]+0,A[1]+1}
     *
     * @param A
     * @return
     */
    public int maxScoreSightseeingPair(int[] A) {
        int f0 = A[0] + A[1] - 1;
        int q = Math.max(A[0], A[1] + 1);
        for (int i = 2; i < A.length; i++) {
            f0 = Math.max(f0, q + A[i] - i);
            q = Math.max(q, A[i] + i);
        }
        return f0;
    }
}
