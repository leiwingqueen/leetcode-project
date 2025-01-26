package wc433

import "sort"

// 给你一个整数数组 nums 和一个正整数 k，返回所有长度最多为 k 的 子序列 中 最大值 与 最小值 之和的总和。
//
//非空子序列 是指从另一个数组中删除一些或不删除任何元素（且不改变剩余元素的顺序）得到的数组。
//
//由于答案可能非常大，请返回对 109 + 7 取余数的结果。
//
//
//
//示例 1：
//
//输入： nums = [1,2,3], k = 2
//
//输出： 24
//
//解释：
//
//数组 nums 中所有长度最多为 2 的子序列如下：
//
//子序列	最小值	最大值	和
//[1]	1	1	2
//[2]	2	2	4
//[3]	3	3	6
//[1, 2]	1	2	3
//[1, 3]	1	3	4
//[2, 3]	2	3	5
//总和	 	 	24
//因此，输出为 24。
//
//示例 2：
//
//输入： nums = [5,0,6], k = 1
//
//输出： 22
//
//解释：
//
//对于长度恰好为 1 的子序列，最小值和最大值均为元素本身。因此，总和为 5 + 5 + 0 + 0 + 6 + 6 = 22。
//
//示例 3：
//
//输入： nums = [1,1,1], k = 2
//
//输出： 12
//
//解释：
//
//子序列 [1, 1] 和 [1] 各出现 3 次。对于所有这些子序列，最小值和最大值均为 1。因此，总和为 12。
//
//
//
//提示：
//
//1 <= nums.length <= 105
//0 <= nums[i] <= 109
//1 <= k <= min(100, nums.length)

// 排序不影响最终的结果，因此我们可以先排序来降低复杂度
// f(n,k)[0]=f(n-1,k)[0]+f(n-1,k-1)[0]
// f(n,k)[1]=f(n-1,k)[1]+C(n-1,k-1)*nums[n-1]
func minMaxSums(nums []int, k int) int {
	mod := 1_000_000_007
	combine := func(n, k int) int {
		res := 1
		for i := 0; i < k; i++ {
			res *= n - i
			res %= mod
		}
		return res
	}
	n := len(nums)
	// 先排序
	sort.Ints(nums)
	dp, tmp := make([][]int, k), make([][]int, k)
	for i := 0; i < k; i++ {
		dp[i] = make([]int, 2)
		tmp[i] = make([]int, 2)
	}
	// 初始化
	res := 0
	dp[0][0] = nums[0]
	dp[0][1] = nums[0]
	res += dp[0][0] + dp[0][1]
	for i := 1; i < n; i++ {
		for j := 0; j <= min(i, k-1); j++ {
			// 选择j+1个数字
			tmp[j][0] = dp[j][0]
			if j > 0 {
				tmp[j][0] += dp[j-1][0]
			}
			tmp[j][1] = dp[j][1]
			tmp[j][1] += combine(i+1, j) * nums[i]
			res += tmp[j][0] + tmp[j][1]
		}
		for j := 0; j < k; j++ {
			dp[j][0] = tmp[j][0]
			dp[j][1] = tmp[j][1]
		}
	}
	return res
}

// 先改成朴素的写法
// f(n,k,0)表示前n个数字取k个数字的最小值,f(n,k,0)对应为最大值
// f(n,k,0)=f(n-1,k,0)+f(n-1,k-1,0)
// f(n,k,1)=f(n-1,k,1)+C(n-1,k-1)*nums[n-1]
func minMaxSums2(nums []int, k int) int {
	mod := 1_000_000_007
	combine := func(n, k int) int {
		res := 1
		for i := 0; i < k; i++ {
			res *= n - i
			res %= mod
		}
		return res
	}
	n := len(nums)
	// 先排序
	sort.Ints(nums)
	dp := make([][][]int, n)
	for i := 0; i < n; i++ {
		dp[i] = make([][]int, k)
		for j := 0; j < k; j++ {
			dp[i][j] = make([]int, 2)
		}
	}
	// 初始化
	res := 0
	dp[0][0][0] = nums[0]
	dp[0][0][1] = nums[1]
	for i := 1; i < n; i++ {
		dp[i][0][0] = dp[i-1][0][0] + nums[i]
		dp[i][0][1] = dp[i-1][0][1] + nums[i]
		res += dp[i][0][0] + dp[i][0][1]
	}
	for i := 1; i < n; i++ {
		for j := 1; j <= min(i, k-1); j++ {
			dp[i][j][0] = dp[i-1][j][0] + dp[i-1][j-1][0]
			dp[i][j][1] = dp[i-1][j][1] + combine(i+1, j)*nums[i]
			res += dp[i][j][0] + dp[i][j][1]
		}
	}
	return res
}
