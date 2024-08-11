package wc410

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
//1 <= nums[i] <= 50

// 简单的DFS,超时
func countOfPairs(nums []int) int {
	n := len(nums)
	var dfs func(idx int, arr1 []int) int
	dfs = func(idx int, arr1 []int) int {
		if idx == n {
			return 1
		}
		res := 0
		for i := 0; i <= nums[idx]; i++ {
			// 选择[0,nums[idx]]之间的数字
			if idx == 0 || (i >= arr1[idx-1] && nums[idx]-i <= nums[idx-1]-arr1[idx-1]) {
				arr1[idx] = i
				cnt := dfs(idx+1, arr1)
				res += cnt
			}
		}
		return res
	}
	return dfs(0, make([]int, n))
}

// 用dp进行优化
// f(n,k)为前n个数字，且arr1的最后一个数字为k的组合数
// f(n,k)=f(n-1,0)+f(n-1,1)+...+f(n-1,k)
func countOfPairs2(nums []int) int {
	mod := 1_000_000_007
	n := len(nums)
	dp := make([][]int, n)
	for i := 0; i < n; i++ {
		dp[i] = make([]int, 51)
	}
	for i := 0; i <= nums[0]; i++ {
		dp[0][i] = 1
	}
	for i := 1; i < n; i++ {
		for j := 0; j <= nums[i]; j++ {
			for l := 0; l <= j; l++ {
				if nums[i]-j <= nums[i-1]-l {
					dp[i][j] = (dp[i][j] + dp[i-1][l]) % mod
				}
			}
		}
	}
	res := 0
	for i := 0; i <= nums[n-1]; i++ {
		res = (res + dp[n-1][i]) % mod
	}
	return res
}
