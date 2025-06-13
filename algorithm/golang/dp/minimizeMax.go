package dp

import "sort"

// 给你一个下标从 0 开始的整数数组 nums 和一个整数 p 。请你从 nums 中找到 p 个下标对，每个下标对对应数值取差值，你需要使得这 p 个差值的 最大值 最小。同时，你需要确保每个下标在这 p 个下标对中最多出现一次。
//
//对于一个下标对 i 和 j ，这一对的差值为 |nums[i] - nums[j]| ，其中 |x| 表示 x 的 绝对值 。
//
//请你返回 p 个下标对对应数值 最大差值 的 最小值 。
//
//
//
//示例 1：
//
//输入：nums = [10,1,2,7,1,3], p = 2
//输出：1
//解释：第一个下标对选择 1 和 4 ，第二个下标对选择 2 和 5 。
//最大差值为 max(|nums[1] - nums[4]|, |nums[2] - nums[5]|) = max(0, 1) = 1 。所以我们返回 1 。
//示例 2：
//
//输入：nums = [4,2,1,2], p = 1
//输出：0
//解释：选择下标 1 和 3 构成下标对。差值为 |2 - 2| = 0 ，这是最大差值的最小值。
//
//
//提示：
//
//1 <= nums.length <= 105
//0 <= nums[i] <= 109
//0 <= p <= (nums.length)/2

// f(n,k)=min(f(n-1,k),max(f(n-2,k-1),nums[n-1]-nums[n-2]))
func minimizeMax(nums []int, p int) int {
	n := len(nums)
	sort.Ints(nums)
	dp := make([][]int, n+1)
	for i := 0; i <= n; i++ {
		dp[i] = make([]int, p+1)
	}
	dp[2][1] = nums[1] - nums[0]
	for i := 3; i <= n; i++ {
		for j := 1; j <= p && j <= i/2; j++ {
			dp[i][j] = max(dp[i-2][j-1], nums[i-1]-nums[i-2])
			if j >= (i-1)/2 {
				dp[i][j] = min(dp[i-1][j], dp[i][j])
			}
		}
	}
	return dp[n][p]
}
