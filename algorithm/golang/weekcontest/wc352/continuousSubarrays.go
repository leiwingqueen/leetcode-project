package wc352

// 给你一个下标从 0 开始的整数数组 nums 。nums 的一个子数组如果满足以下条件，那么它是 不间断 的：
//
//i，i + 1 ，...，j  表示子数组中的下标。对于所有满足 i <= i1, i2 <= j 的下标对，都有 0 <= |nums[i1] - nums[i2]| <= 2 。
//请你返回 不间断 子数组的总数目。
//
//子数组是一个数组中一段连续 非空 的元素序列。
//
//
//
//示例 1：
//
//输入：nums = [5,4,2,4]
//输出：8
//解释：
//大小为 1 的不间断子数组：[5], [4], [2], [4] 。
//大小为 2 的不间断子数组：[5,4], [4,2], [2,4] 。
//大小为 3 的不间断子数组：[4,2,4] 。
//没有大小为 4 的不间断子数组。
//不间断子数组的总数目为 4 + 3 + 1 = 8 。
//除了这些以外，没有别的不间断子数组。
//示例 2：
//
//输入：nums = [1,2,3]
//输出：6
//解释：
//大小为 1 的不间断子数组：[1], [2], [3] 。
//大小为 2 的不间断子数组：[1,2], [2,3] 。
//大小为 3 的不间断子数组：[1,2,3] 。
//不间断子数组的总数目为 3 + 2 + 1 = 6 。
//
//
//提示：
//
//1 <= nums.length <= 105
//1 <= nums[i] <= 109

// 双指针+单调栈
func continuousSubarrays(nums []int) int64 {
	abs := func(a int) int {
		if a < 0 {
			return -a
		} else {
			return a
		}
	}
	n := len(nums)
	l, r := 0, 0
	var sMax, sMin []int
	var res int64
	for r < n {
		// 问题在于如何维护一个区间的最大和最小值
		if len(sMax) == 0 {
			sMax = append(sMax, r)
			sMin = append(sMin, r)
			res++
			r++
		} else {
			max, min := nums[sMax[0]], nums[sMin[0]]
			if abs(nums[r]-min) <= 2 && abs(nums[r]-max) <= 2 {
				// 更新堆栈
				for len(sMax) > 0 && nums[sMax[len(sMax)-1]] < nums[r] {
					sMax = sMax[:len(sMax)-1]
				}
				sMax = append(sMax, r)
				for len(sMin) > 0 && nums[sMin[len(sMin)-1]] > nums[r] {
					sMin = sMin[:len(sMin)-1]
				}
				sMin = append(sMin, r)
				r++
				res += int64(r - l)
			} else {
				// 左边指针移动
				if sMax[0] == l {
					sMax = sMax[1:]
				}
				if sMin[0] == l {
					sMin = sMin[1:]
				}
				l++
			}
		}
	}
	return res
}
