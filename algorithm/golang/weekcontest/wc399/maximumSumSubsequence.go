package wc399

// 给你一个整数数组 nums 和一个二维数组 queries，其中 queries[i] = [posi, xi]。
//
//对于每个查询 i，首先将 nums[posi] 设置为 xi，然后计算查询 i 的答案，该答案为 nums 中 不包含相邻元素 的
//子序列
// 的 最大 和。
//
//返回所有查询的答案之和。
//
//由于最终答案可能非常大，返回其对 109 + 7 取余 的结果。
//
//子序列 是指从另一个数组中删除一些或不删除元素而不改变剩余元素顺序得到的数组。
//
//
//
//示例 1：
//
//输入：nums = [3,5,9], queries = [[1,-2],[0,-3]]
//
//输出：21
//
//解释：
//执行第 1 个查询后，nums = [3,-2,9]，不包含相邻元素的子序列的最大和为 3 + 9 = 12。
//执行第 2 个查询后，nums = [-3,-2,9]，不包含相邻元素的子序列的最大和为 9 。
//
//示例 2：
//
//输入：nums = [0,-1], queries = [[0,-5]]
//
//输出：0
//
//解释：
//执行第 1 个查询后，nums = [-5,-1]，不包含相邻元素的子序列的最大和为 0（选择空子序列）。
//
//
//
//提示：
//
//1 <= nums.length <= 5 * 104
//-105 <= nums[i] <= 105
//1 <= queries.length <= 5 * 104
//queries[i] == [posi, xi]
//0 <= posi <= nums.length - 1
//-105 <= xi <= 105

// 超时
func maximumSumSubsequence(nums []int, queries [][]int) int {
	n := len(nums)
	dp := make([]int, n+1)
	dp[0] = 0
	dp[1] = max(nums[0], 0)
	for i := 2; i <= n; i++ {
		dp[i] = max(dp[i-1], dp[i-2]+nums[i-1])
	}
	mod := 1_000_000_007
	res := 0
	for _, query := range queries {
		pos, x := query[0], query[1]
		nums[pos] = x
		if pos == 0 {
			dp[1] = max(nums[0], 0)
		}
		// 只需要修改dp[pos+1],
		for j := max(pos+1, 2); j <= n; j++ {
			dp[j] = max(dp[j-1], dp[j-2]+nums[j-1])
		}
		res = (res + dp[n]) % mod
	}
	return res
}

// 错误
func maximumSumSubsequence2(nums []int, queries [][]int) int {
	n := len(nums)
	dp := make([]int, n+1)
	dp[0] = 0
	dp[1] = max(nums[0], 0)
	for i := 2; i <= n; i++ {
		dp[i] = max(dp[i-1], dp[i-2]+nums[i-1])
	}
	mod := 1_000_000_007
	res := 0
	for _, query := range queries {
		pos, x := query[0], query[1]
		nums[pos] = x
		if pos == 0 {
			dp[1] = max(nums[0], 0)
		}
		// 只需要修改dp[pos+1]往后的元素
		for j := max(pos+1, 2); j <= n; j++ {
			tmp := max(dp[j-1], dp[j-2]+nums[j-1])
			if tmp <= dp[j] {
				dp[j] = tmp
				break
			}
			dp[j] = tmp
		}
		res = (res + dp[n]) % mod
	}
	return res
}

// TODO: 线段树
