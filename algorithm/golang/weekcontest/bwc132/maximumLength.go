package bwc132

// 给你一个整数数组 nums 和一个 非负 整数 k 。如果一个整数序列 seq 满足在范围下标范围 [0, seq.length - 2] 中存在 不超过 k 个下标 i 满足 seq[i] != seq[i + 1] ，那么我们称这个整数序列为 好 序列。
//
//请你返回 nums 中 好 子序列 的最长长度
//
//
//
//示例 1：
//
//输入：nums = [1,2,1,1,3], k = 2
//
//输出：4
//
//解释：
//
//最长好子序列为 [1,2,1,1,3] 。
//
//示例 2：
//
//输入：nums = [1,2,3,4,5,1], k = 0
//
//输出：2
//
//解释：
//
//最长好子序列为 [1,2,3,4,5,1] 。
//
//
//
//提示：
//
//1 <= nums.length <= 500
//1 <= nums[i] <= 109
//0 <= k <= min(nums.length, 25)

// 假设f(n,t)为前n个数组中不超过长度为t的最长子序列长度，并且以nums[n-1]为最后一个数字
// if nums[n-1]!=nums[n-2],f(n,t)=f(n-1,t-1)+1
// else f(n,t)=f(n-1,t)+1
func maximumLength(nums []int, k int) int {
	n := len(nums)
	dp := make([][]int, n)
	for i := 0; i < n; i++ {
		dp[i] = make([]int, k+1)
	}
	// 初始化
	for i := 0; i <= k; i++ {
		dp[0][i] = 1
	}
	for i := 1; i < n; i++ {
		for j := 0; j <= k; j++ {
			for l := 0; l < i; l++ {
				if nums[i] != nums[l] {
					// 特殊情况j=0
					if j == 0 {
						dp[i][j] = max(dp[i][j], 1)
					} else {
						dp[i][j] = max(dp[i][j], dp[l][j-1]+1)
					}
				} else {
					dp[i][j] = max(dp[i][j], dp[l][j]+1)
				}
			}
		}
	}
	// 求最后的解
	res := 0
	for i := 0; i < n; i++ {
		res = max(res, dp[i][k])
	}
	return res
}
