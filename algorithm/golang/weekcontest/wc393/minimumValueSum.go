package wc393

// 给你两个数组 nums 和 andValues，长度分别为 n 和 m。
//
// 数组的 值 等于该数组的 最后一个 元素。
//
// 你需要将 nums 划分为 m 个 不相交的连续 子数组，对于第 ith 个子数组 [li, ri]，子数组元素的按位AND运算结果等于 andValues[i]，换句话说，对所有的 1 <= i <= m，nums[li] & nums[li + 1] & ... & nums[ri] == andValues[i] ，其中 & 表示按位AND运算符。
//
// 返回将 nums 划分为 m 个子数组所能得到的可能的 最小 子数组 值 之和。如果无法完成这样的划分，则返回 -1 。
//
// 示例 1：
//
// 输入： nums = [1,4,3,3,2], andValues = [0,3,3,2]
//
// 输出： 12
//
// 解释：
//
// 唯一可能的划分方法为：
//
// [1,4] 因为 1 & 4 == 0
// [3] 因为单元素子数组的按位 AND 结果就是该元素本身
// [3] 因为单元素子数组的按位 AND 结果就是该元素本身
// [2] 因为单元素子数组的按位 AND 结果就是该元素本身
// 这些子数组的值之和为 4 + 3 + 3 + 2 = 12
//
// 示例 2：
//
// 输入： nums = [2,3,5,7,7,7,5], andValues = [0,7,5]
//
// 输出： 17
//
// 解释：
//
// 划分 nums 的三种方式为：
//
// [[2,3,5],[7,7,7],[5]] 其中子数组的值之和为 5 + 7 + 5 = 17
// [[2,3,5,7],[7,7],[5]] 其中子数组的值之和为 7 + 7 + 5 = 19
// [[2,3,5,7,7],[7],[5]] 其中子数组的值之和为 7 + 7 + 5 = 19
// 子数组值之和的最小可能值为 17
//
// 示例 3：
//
// 输入： nums = [1,2,3,4], andValues = [2]
//
// 输出： -1
//
// 解释：
//
// 整个数组 nums 的按位 AND 结果为 0。由于无法将 nums 划分为单个子数组使得元素的按位 AND 结果为 2，因此返回 -1。
//
// 提示：
//
// 1 <= n == nums.length <= 104
// 1 <= m == andValues.length <= min(n, 10)
// 1 <= nums[i] < 105
// 0 <= andValues[j] < 105

// f(i,j)表示nums的前i个数字，构成前j个andValues的最小值的和
// f(i,j)=min(f(i-k,j-1)+nums[i-1])，其中i-k>=j-1，并且nums[i-k:i]的与为andValues[j-1]
// 超时
func minimumValueSum(nums []int, andValues []int) int {
	n, m := len(nums), len(andValues)
	dp := make([][]int, m)
	for i := 0; i < m; i++ {
		dp[i] = make([]int, n)
	}
	// 初始化
	and := nums[0]
	for i := 0; i < n; i++ {
		and &= nums[i]
		if and == andValues[0] {
			dp[0][i] = nums[i]
		} else {
			dp[0][i] = -1
		}
	}
	// dp迭代
	for i := 1; i < m; i++ {
		for j := n - 1; j >= i; j-- {
			and = nums[j]
			dp[i][j] = -1
			for k := j - 1; k >= i-1; k-- {
				if and == andValues[i] {
					if dp[i-1][k] >= 0 && (dp[i][j] < 0 || dp[i-1][k]+nums[j] < dp[i][j]) {
						dp[i][j] = dp[i-1][k] + nums[j]
					}
				}
				and &= nums[k]
			}
		}
	}
	return dp[m-1][n-1]
}

// 简单优化，还是超时
func minimumValueSum2(nums []int, andValues []int) int {
	n, m := len(nums), len(andValues)
	dp := make([][]int, m)
	for i := 0; i < m; i++ {
		dp[i] = make([]int, n)
	}
	// 初始化
	and := nums[0]
	for i := 0; i < n; i++ {
		and &= nums[i]
		if and == andValues[0] {
			dp[0][i] = nums[i]
		} else {
			dp[0][i] = -1
		}
	}
	// dp迭代
	for i := 1; i < m; i++ {
		for j := n - 1; j >= i; j-- {
			and = nums[j]
			dp[i][j] = -1
			// 问题在于如果快速地找到and满足条件的
			for k := j - 1; k >= i-1; k-- {
				if and < andValues[i] {
					break
				} else if and == andValues[i] {
					if dp[i-1][k] >= 0 && (dp[i][j] < 0 || dp[i-1][k]+nums[j] < dp[i][j]) {
						dp[i][j] = dp[i-1][k] + nums[j]
					}
				}
				and &= nums[k]
			}
		}
	}
	return dp[m-1][n-1]
}

// 简单优化，还是超时
func minimumValueSum3(nums []int, andValues []int) int {
	n, m := len(nums), len(andValues)
	dp := make([][]int, m)
	for i := 0; i < m; i++ {
		dp[i] = make([]int, n)
	}
	// 初始化
	and := nums[0]
	for i := 0; i < n; i++ {
		and &= nums[i]
		if and == andValues[0] {
			dp[0][i] = nums[i]
		} else {
			dp[0][i] = -1
		}
	}
	// dp迭代
	for i := 1; i < m; i++ {
		for j := n - 1; j >= i; j-- {
			and = nums[j]
			dp[i][j] = -1
			// 问题在于如果快速地找到and满足条件的，二分查找

			for k := j - 1; k >= i-1; k-- {
				if and < andValues[i] {
					break
				} else if and == andValues[i] {
					if dp[i-1][k] >= 0 && (dp[i][j] < 0 || dp[i-1][k]+nums[j] < dp[i][j]) {
						dp[i][j] = dp[i-1][k] + nums[j]
					}
				}
				and &= nums[k]
			}
		}
	}
	return dp[m-1][n-1]
}
