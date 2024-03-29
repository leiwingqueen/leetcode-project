package com.liyongquan.dp;

//1231. 分享巧克力
//你有一大块巧克力，它由一些甜度不完全相同的小块组成。我们用数组 sweetness 来表示每一小块的甜度。
//
//你打算和 K 名朋友一起分享这块巧克力，所以你需要将切割 K 次才能得到 K+1 块，每一块都由一些 连续 的小块组成。
//
//为了表现出你的慷慨，你将会吃掉 总甜度最小 的一块，并将其余几块分给你的朋友们。
//
//请找出一个最佳的切割策略，使得你所分得的巧克力 总甜度最大，并返回这个 最大总甜度。
//
// 
//
//示例 1：
//
//输入：sweetness = [1,2,3,4,5,6,7,8,9], K = 5
//输出：6
//解释：你可以把巧克力分成 [1,2,3], [4,5], [6], [7], [8], [9]。
//示例 2：
//
//输入：sweetness = [5,6,7,8,9,1,2,3,4], K = 8
//输出：1
//解释：只有一种办法可以把巧克力分成 9 块。
//示例 3：
//
//输入：sweetness = [1,2,2,1,2,2,1,2,2], K = 2
//输出：5
//解释：你可以把巧克力分成 [1,2,2], [1,2,2], [1,2,2]。
// 
//
//提示：
//
//0 <= K < sweetness.length <= 10^4
//1 <= sweetness[i] <= 10^5
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/divide-chocolate
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。


public class MaximizeSweetness {
    /**
     * dp表达式
     * <p>
     * f(n,k)=max{min{f(n-1,k-1),A[i-1]},min{f(n-2,k-1),A[i-1]+A[i-2]},...}
     * <p>
     * 其中k<=n
     * <p>
     * 时间复杂度O(n^2*k)
     * <p>
     * 超时
     * <p>
     * 这个数量级只能考虑二分
     *
     * @param sweetness
     * @param k
     * @return
     */
    public int maximizeSweetness(int[] sweetness, int k) {
        int n = sweetness.length;
        //前n个巧克力，切k刀,其中k<n
        int[][] dp = new int[n][k + 1];
        //初始化
        dp[0][0] = sweetness[0];
        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i - 1][0] + sweetness[i];
        }
        //对角线
        for (int i = 1; i < n && i <= k; i++) {
            dp[i][i] = Math.min(dp[i - 1][i - 1], sweetness[i]);
        }
        //前缀和
        int[] preSum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            preSum[i] = preSum[i - 1] + sweetness[i - 1];
        }
        //dp迭代
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < i && j <= k; j++) {
                int max = 0;
                for (int l = j - 1; l < i; l++) {
                    //[l+1,i+1)
                    int r = Math.min(dp[l][j - 1], preSum[i + 1] - preSum[l + 1]);
                    max = Math.max(max, r);
                }
                dp[i][j] = max;
            }
        }
        return dp[n - 1][k];
    }

    /**
     * 二分查找
     *
     * @param sweetness
     * @param k
     * @return
     */
    public int maximizeSweetness2(int[] sweetness, int k) {
        int n = sweetness.length;
        int min = Integer.MAX_VALUE;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            min = Math.min(sweetness[i], min);
            sum += sweetness[i];
        }
        int l = min, r = sum;
        while (l < r) {
            int mid = l + (r - l + 1) / 2;
            if (check(sweetness, k, mid)) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }

    private boolean check(int[] sweetness, int k, int s) {
        int n = sweetness.length;
        int idx = 0;
        int cnt = 0;
        while (idx < n && cnt < k + 1) {
            int sum = 0;
            while (idx < n && sum < s) {
                sum += sweetness[idx++];
            }
            if (sum < s) {
                return false;
            }
            cnt++;
        }
        return cnt >= k + 1;
    }
}
