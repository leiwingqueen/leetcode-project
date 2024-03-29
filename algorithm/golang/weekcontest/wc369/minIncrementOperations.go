package wc369

// 给你一个下标从 0 开始、长度为 n 的整数数组 nums ，和一个整数 k 。
//
//你可以执行下述 递增 运算 任意 次（可以是 0 次）：
//
//从范围 [0, n - 1] 中选择一个下标 i ，并将 nums[i] 的值加 1 。
//如果数组中任何长度 大于或等于 3 的子数组，其 最大 元素都大于或等于 k ，则认为数组是一个 美丽数组 。
//
//以整数形式返回使数组变为 美丽数组 需要执行的 最小 递增运算数。
//
//子数组是数组中的一个连续 非空 元素序列。
//
//
//
//示例 1：
//
//输入：nums = [2,3,0,0,2], k = 4
//输出：3
//解释：可以执行下述递增运算，使 nums 变为美丽数组：
//选择下标 i = 1 ，并且将 nums[1] 的值加 1 -> [2,4,0,0,2] 。
//选择下标 i = 4 ，并且将 nums[4] 的值加 1 -> [2,4,0,0,3] 。
//选择下标 i = 4 ，并且将 nums[4] 的值加 1 -> [2,4,0,0,4] 。
//长度大于或等于 3 的子数组为 [2,4,0], [4,0,0], [0,0,4], [2,4,0,0], [4,0,0,4], [2,4,0,0,4] 。
//在所有子数组中，最大元素都等于 k = 4 ，所以 nums 现在是美丽数组。
//可以证明无法用少于 3 次递增运算使 nums 变为美丽数组。
//因此，答案为 3 。
//示例 2：
//
//输入：nums = [0,1,3,3], k = 5
//输出：2
//解释：可以执行下述递增运算，使 nums 变为美丽数组：
//选择下标 i = 2 ，并且将 nums[2] 的值加 1 -> [0,1,4,3] 。
//选择下标 i = 2 ，并且将 nums[2] 的值加 1 -> [0,1,5,3] 。
//长度大于或等于 3 的子数组为 [0,1,5]、[1,5,3]、[0,1,5,3] 。
//在所有子数组中，最大元素都等于 k = 5 ，所以 nums 现在是美丽数组。
//可以证明无法用少于 2 次递增运算使 nums 变为美丽数组。
//因此，答案为 2 。
//示例 3：
//
//输入：nums = [1,1,2], k = 1
//输出：0
//解释：在这个示例中，只有一个长度大于或等于 3 的子数组 [1,1,2] 。
//其最大元素 2 已经大于 k = 1 ，所以无需执行任何增量运算。
//因此，答案为 0 。
//
//
//提示：
//
//3 <= n == nums.length <= 105
//0 <= nums[i] <= 109
//0 <= k <= 109

func minIncrementOperations(nums []int, k int) int64 {
	max := func(a, b int64) int64 {
		if a > b {
			return a
		} else {
			return b
		}
	}
	min := func(a, b int64) int64 {
		if a < b {
			return a
		} else {
			return b
		}
	}
	n := len(nums)
	dp := make([]int64, n+1)
	dp[0] = 0
	dp[1] = max(int64(k-nums[0]), 0)
	dp[2] = min(dp[1], max(int64(k-nums[1]), 0))
	dp[3] = min(dp[2], max(int64(k-nums[2]), 0))
	dp[1] = 0
	dp[2] = 0
	for i := 4; i <= n; i++ {
		dp[i] = min(min(dp[i-1]+max(int64(k-nums[i-1]), 0), dp[i-2]+max(int64(k-nums[i-2]), 0)),
			dp[i-3]+max(int64(k-nums[i-3]), 0))
	}
	return dp[n]
}

// 优化写法
func minIncrementOperations2(nums []int, k int) int64 {
	max := func(a, b int64) int64 {
		if a > b {
			return a
		} else {
			return b
		}
	}
	min := func(a, b int64) int64 {
		if a < b {
			return a
		} else {
			return b
		}
	}
	n := len(nums)
	dp := make([]int64, n)
	dp[2] = min(min(max(int64(k-nums[0]), 0), max(int64(k-nums[1]), 0)), max(int64(k-nums[2]), 0))
	for i := 3; i < n; i++ {
		dp[i] = min(min(dp[i-1]+max(int64(k-nums[i]), 0), dp[i-2]+max(int64(k-nums[i-1]), 0)),
			dp[i-3]+max(int64(k-nums[i-2]), 0))
	}
	return dp[n-1]
}

func minIncrementOperations3(nums []int, k int) int64 {
	max := func(a, b int64) int64 {
		if a > b {
			return a
		} else {
			return b
		}
	}
	min := func(a, b int64) int64 {
		if a < b {
			return a
		} else {
			return b
		}
	}
	n := len(nums)
	f0, f1, f2 := int64(0), int64(0), min(min(max(int64(k-nums[0]), 0), max(int64(k-nums[1]), 0)), max(int64(k-nums[2]), 0))
	for i := 3; i < n; i++ {
		fn := min(min(f2+max(int64(k-nums[i]), 0), f1+max(int64(k-nums[i-1]), 0)),
			f0+max(int64(k-nums[i-2]), 0))
		f0 = f1
		f1 = f2
		f2 = fn
	}
	return f2
}
