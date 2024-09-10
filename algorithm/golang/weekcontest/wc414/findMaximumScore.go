package wc414

// 给你一个长度为 n 的整数数组 nums 。
//
//你的目标是从下标 0 出发，到达下标 n - 1 处。每次你只能移动到 更大 的下标处。
//
//从下标 i 跳到下标 j 的得分为 (j - i) * nums[i] 。
//
//请你返回你到达最后一个下标处能得到的 最大总得分 。
//
//
//
//示例 1：
//
//输入：nums = [1,3,1,5]
//
//输出：7
//
//解释：
//
//一开始跳到下标 1 处，然后跳到最后一个下标处。总得分为 1 * 1 + 2 * 3 = 7 。
//
//示例 2：
//
//输入：nums = [4,3,1,3,2]
//
//输出：16
//
//解释：
//
//直接跳到最后一个下标处。总得分为 4 * 4 = 16 。
//
//
//
//提示：
//
//1 <= nums.length <= 105
//1 <= nums[i] <= 105

// 朴素的dp，必然会超时
func findMaximumScore(nums []int) int64 {
	n := len(nums)
	dp := make([]int64, n)
	dp[0] = 0
	for i := 1; i < n; i++ {
		for j := 0; j < i; j++ {
			dp[i] = max(dp[i], dp[j]+int64(nums[j])*int64(i-j))
		}
	}
	return dp[n-1]
}

// 居然是贪心
func findMaximumScore2(nums []int) int64 {
	n := len(nums)
	j := 0
	var res int64
	for i := 1; i < n; i++ {
		if i == n-1 || nums[i] > nums[j] {
			res += int64(nums[j]) * int64(i-j)
			j = i
		}
	}
	return res
}
