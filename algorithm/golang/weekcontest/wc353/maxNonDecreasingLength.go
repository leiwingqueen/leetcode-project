package wc353

// 2771. 构造最长非递减子数组 显示英文描述
//给你两个下标从 0 开始的整数数组 nums1 和 nums2 ，长度均为 n 。
//
//让我们定义另一个下标从 0 开始、长度为 n 的整数数组，nums3 。对于范围 [0, n - 1] 的每个下标 i ，你可以将 nums1[i] 或 nums2[i] 的值赋给 nums3[i] 。
//
//你的任务是使用最优策略为 nums3 赋值，以最大化 nums3 中 最长非递减子数组 的长度。
//
//以整数形式表示并返回 nums3 中 最长非递减 子数组的长度。
//
//注意：子数组 是数组中的一个连续非空元素序列。

func maxNonDecreasingLength(nums1 []int, nums2 []int) int {
	n := len(nums1)
	dp0, dp1 := make([]int, n), make([]int, n)
	for i := 0; i < n; i++ {
		dp0[i] = 1
		dp1[i] = 1
	}
	res := 1
	for i := 1; i < n; i++ {
		if nums1[i] >= nums1[i-1] {
			dp0[i] = dp0[i-1] + 1
		}
		if nums1[i] >= nums2[i-1] && dp1[i-1]+1 > dp0[i] {
			dp0[i] = dp1[i-1] + 1
		}
		if nums2[i] >= nums1[i-1] {
			dp1[i] = dp0[i-1] + 1
		}
		if nums2[i] >= nums2[i-1] && dp1[i-1]+1 > dp1[i] {
			dp1[i] = dp1[i-1] + 1
		}
		if dp0[i] > res {
			res = dp0[i]
		}
		if dp1[i] > res {
			res = dp1[i]
		}
	}
	return res
}
