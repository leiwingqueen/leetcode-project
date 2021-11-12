package com.liyongquan.dp;

//629. K个逆序对数组
//给出两个整数 n 和 k，找出所有包含从 1 到 n 的数字，且恰好拥有 k 个逆序对的不同的数组的个数。
//
//逆序对的定义如下：对于数组的第i个和第 j个元素，如果满i < j且 a[i] > a[j]，则其为一个逆序对；否则不是。
//
//由于答案可能很大，只需要返回 答案 mod 109 + 7 的值。
//
//示例 1:
//
//输入: n = 3, k = 0
//输出: 1
//解释:
//只有数组 [1,2,3] 包含了从1到3的整数并且正好拥有 0 个逆序对。
//示例 2:
//
//输入: n = 3, k = 1
//输出: 2
//解释:
//数组 [1,3,2] 和 [2,1,3] 都有 1 个逆序对。
//说明:
//
// n 的范围是 [1, 1000] 并且 k 的范围是 [0, 1000]。
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/k-inverse-pairs-array
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

/**
 * @author liyongquan
 * @date 2021/11/11
 */
public class KInversePairs {
    /**
     * dp表达式，分别对应最后一个数字n插入不同的位置，从末尾一直插入到头部
     * f(n,k)=f(n-1,k)+f(n-1,k-1)+...+f(n-1,k-n+1)
     * <p>
     * 初始化
     * f(0,0)=1
     * f(0,i)=0，其中i>0
     * <p>
     * 超时，时间复杂度O(n*k^2)
     *
     * @param n
     * @param k
     * @return
     */
    public int kInversePairs(int n, int k) {
        int mod = 1000000007;
        int[][] dp = new int[n][k + 1];
        dp[0][0] = 1;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= k; j++) {
                for (int l = j; l >= 0 && l >= j - i; l--) {
                    dp[i][j] += dp[i - 1][l];
                    dp[i][j] %= mod;
                }
            }
        }
        return dp[n - 1][k];
    }

    /**
     * 上面至少证明了dp表达式的正确性，这里我们只需要考虑如何提高下我们的性能
     * <p>
     * 在第三层循环我们需要计算[j-i,j]这个范围的值，是否可以增加一个前缀和来简化计算？
     * <p>
     * 时间复杂度O(n*k)
     * <p>
     * 空间复杂度还能继续优化下，dp我们就不存了，直接保留p1和p2即可
     *
     * @param n
     * @param k
     * @return
     */
    public int kInversePairs2(int n, int k) {
        int mod = 1000000007;
        int[][] dp = new int[n][k + 1];
        dp[0][0] = 1;
        //保存前缀和
        int[] p1 = new int[k + 2];
        for (int i = 1; i <= k + 1; i++) {
            p1[i] = p1[i - 1] + dp[0][i - 1];
        }
        int[] p2 = new int[k + 2];
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= k; j++) {
                //这里可以简化
                dp[i][j] = (p1[j + 1] - p1[Math.max(j - i, 0)] + mod) % mod;
                /* for (int l = j; l >= 0 && l >= j - i; l--) {
                    dp[i][j] += dp[i - 1][l];
                    dp[i][j] %= mod;
                }*/
                p2[j + 1] = (p2[j] + dp[i][j]) % mod;
            }
            //交换p1,p2
            int[] tmp = p1;
            p1 = p2;
            p2 = tmp;
        }
        return dp[n - 1][k];
    }

    /**
     * 在上面基础上继续优化下空间
     *
     * @param n
     * @param k
     * @return
     */
    public int kInversePairs3(int n, int k) {
        int mod = 1000000007;
        //保存前缀和
        int[] p1 = new int[k + 2];
        for (int i = 1; i <= k + 1; i++) {
            p1[i] = 1;
        }
        int[] p2 = new int[k + 2];
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= k; j++) {
                //这里可以简化
                int dp = (p1[j + 1] - p1[Math.max(j - i, 0)] + mod) % mod;
                p2[j + 1] = (p2[j] + dp) % mod;
            }
            //交换p1,p2
            int[] tmp = p1;
            p1 = p2;
            p2 = tmp;
        }
        return (p1[k + 1] - p1[k] + mod) % mod;
    }
}
