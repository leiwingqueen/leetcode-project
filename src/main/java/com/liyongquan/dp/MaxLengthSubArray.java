package com.liyongquan.dp;

/**
 * 给两个整数数组 A 和 B ，返回两个数组中公共的、长度最长的子数组的长度。
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * 输入：
 * A: [1,2,3,2,1]
 * B: [3,2,1,4,7]
 * 输出：3
 * 解释：
 * 长度最长的公共子数组是 [3, 2, 1] 。
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= len(A), len(B) <= 1000
 * 0 <= A[i], B[i] < 100
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-length-of-repeated-subarray
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MaxLengthSubArray {
    /**
     * 定义dp[i][j]为以A[i]和B[j]开头的最长的公共字符串，则有
     * <p>
     * dp[i][j]=A[i]==B[j]?dp[i+1][j+1]+1:0
     * <p>
     * 初始化：
     * 为了简化整个初始化过程，我们为dp这个二维数组增加一列，并初始化为0.分别从下到上，从右到左逐步填满整个二维数组
     * <p>
     * 最终结果为max{dp}
     *
     * @param A
     * @param B
     * @return
     */
    public int findLength(int[] A, int[] B) {
        int max = 0;
        int[][] dp = new int[A.length + 1][B.length + 1];
        for (int i = A.length - 1; i >= 0; i--) {
            for (int j = B.length - 1; j >= 0; j--) {
                dp[i][j] = A[i] == B[j] ? (dp[i + 1][j + 1] + 1) : 0;
                if (dp[i][j] > max) {
                    max = dp[i][j];
                }
            }
        }
        return max;
    }
}
