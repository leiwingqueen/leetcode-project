package wc410

import "sort"

// 给你一个长度为 n 的 正 整数数组 nums 。
//
//如果两个 非负 整数数组 (arr1, arr2) 满足以下条件，我们称它们是 单调 数组对：
//
//两个数组的长度都是 n 。
//arr1 是单调 非递减 的，换句话说 arr1[0] <= arr1[1] <= ... <= arr1[n - 1] 。
//arr2 是单调 非递增 的，换句话说 arr2[0] >= arr2[1] >= ... >= arr2[n - 1] 。
//对于所有的 0 <= i <= n - 1 都有 arr1[i] + arr2[i] == nums[i] 。
//请你返回所有 单调 数组对的数目。
//
//由于答案可能很大，请你将它对 109 + 7 取余 后返回。
//
//
//
//示例 1：
//
//输入：nums = [2,3,2]
//
//输出：4
//
//解释：
//
//单调数组对包括：
//
//([0, 1, 1], [2, 2, 1])
//([0, 1, 2], [2, 2, 0])
//([0, 2, 2], [2, 1, 0])
//([1, 2, 2], [1, 1, 0])
//示例 2：
//
//输入：nums = [5,5,5,5]
//
//输出：126
//
//
//
//提示：
//
//1 <= n == nums.length <= 2000
//1 <= nums[i] <= 1000

// 还是有错误
func countOfPairs3(nums []int) int {
	mod := 1_000_000_007
	mx := 1000
	n := len(nums)
	dp := make([][]int, n)
	for i := 0; i < n; i++ {
		dp[i] = make([]int, mx+1)
	}
	pre, cur := make([]int, mx+2), make([]int, mx+2)
	for i := 0; i <= mx; i++ {
		pre[i+1] = pre[i]
		if i <= nums[0] {
			dp[0][i] = 1
			pre[i+1] = (pre[i+1] + 1) % mod
		}
	}
	for i := 1; i < n; i++ {
		for j := 0; j <= mx; j++ {
			cur[j+1] = cur[j]
			if j <= nums[i] {
				// arr1选择j，那么arr2选择nums[i]-j
				// l查找可以选择的最大值，这里应该可以通过二分进行查找
				l := sort.Search(j+1, func(k int) bool {
					return nums[i-1]-k < nums[i]-j
				})
				if l > 0 {
					// 这里就求dp[i-1][0]+...+dp[i-1][l-1]的和
					dp[i][j] = pre[l]
				}
				cur[j+1] = (cur[j+1] + dp[i][j]) % mod
			}
		}
		copy(pre, cur)
	}
	return pre[mx+1]
}
