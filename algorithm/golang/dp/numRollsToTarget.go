package dp

// 这里有 n 个一样的骰子，每个骰子上都有 k 个面，分别标号为 1 到 k 。
//
//给定三个整数 n ,  k 和 target ，返回可能的方式(从总共 kn 种方式中)滚动骰子的数量，使正面朝上的数字之和等于 target 。
//
//答案可能很大，你需要对 109 + 7 取模 。
//
//
//
//示例 1：
//
//输入：n = 1, k = 6, target = 3
//输出：1
//解释：你扔一个有 6 个面的骰子。
//得到 3 的和只有一种方法。
//示例 2：
//
//输入：n = 2, k = 6, target = 7
//输出：6
//解释：你扔两个骰子，每个骰子有 6 个面。
//得到 7 的和有 6 种方法：1+6 2+5 3+4 4+3 5+2 6+1。
//示例 3：
//
//输入：n = 30, k = 30, target = 500
//输出：222616187
//解释：返回的结果必须是对 109 + 7 取模。
//
//
//提示：
//
//1 <= n, k <= 30
//1 <= target <= 1000

// dp解法
func numRollsToTarget(n int, k int, target int) int {
	mod := 1_000_000_007
	dp := make([]int, target+1)
	for i := 1; i <= k && i <= target; i++ {
		dp[i] = 1
	}
	// 摇i次得到总和为s，则s的范围[i,k*i]
	for i := 2; i <= n; i++ {
		tmp := make([]int, target+1)
		for j := i; j <= target && j <= k*i; j++ {
			for l := 1; l <= k && l < j; l++ {
				tmp[j] = (tmp[j] + dp[j-l]) % mod
			}
		}
		dp = tmp
	}
	return dp[target]
}
