package bwc154

import "math"

// 给你一个整数数组 nums 和一个整数 k。你可以执行以下操作任意次：
//
//选择一个下标 i，并将 nums[i] 替换为 nums[i] - 1。
//返回使数组元素之和能被 k 整除所需的最小操作次数。
//
//
//
//示例 1：
//
//输入： nums = [3,9,7], k = 5
//
//输出： 4
//
//解释：
//
//对 nums[1] = 9 执行 4 次操作。现在 nums = [3, 5, 7]。
//数组之和为 15，可以被 5 整除。
//示例 2：
//
//输入： nums = [4,1,3], k = 4
//
//输出： 0
//
//解释：
//
//数组之和为 8，已经可以被 4 整除。因此不需要操作。
//示例 3：
//
//输入： nums = [3,2], k = 6
//
//输出： 5
//
//解释：
//
//对 nums[0] = 3 执行 3 次操作，对 nums[1] = 2 执行 2 次操作。现在 nums = [0, 0]。
//数组之和为 0，可以被 6 整除。
//
//
//提示：
//
//1 <= nums.length <= 1000
//1 <= nums[i] <= 1000
//1 <= k <= 100

// 典型的DP题目
// 第一题简单题居然超时
// 时间复杂度O(n*k*k)
func minOperations(nums []int, k int) int {
	n := len(nums)
	dp := make([][]int, n)
	for i := 0; i < n; i++ {
		dp[i] = make([]int, k)
	}
	// 初始化
	for i := 0; i < k; i++ {
		dp[0][i] = (k + nums[0]%k - i) % k
	}
	// 迭代
	for i := 1; i < n; i++ {
		for j := 0; j < k; j++ {
			dp[i][j] = math.MaxInt32
			for l := 0; l < k; l++ {
				// 假设最后一个数字减去l
				mod := (k + nums[i]%k - l) % k
				expect := (j + k - mod) % k
				dp[i][j] = min(dp[i][j], dp[i-1][expect]+l)
			}
		}
	}
	return dp[n-1][0]
}

// 傻逼了
func minOperations2(nums []int, k int) int {
	sum := 0
	for _, num := range nums {
		sum += num
	}
	return sum % k
}
