package wc392

import (
	"sort"
)

// 给你一个整数数组 nums 和一个 非负 整数 k 。一次操作中，你可以选择任一元素 加 1 或者减 1 。
//
//请你返回将 nums 中位数 变为 k 所需要的 最少 操作次数。
//
//一个数组的中位数指的是数组按非递减顺序排序后最中间的元素。如果数组长度为偶数，我们选择中间两个数的较大值为中位数。
//
//
//
//示例 1：
//
//输入：nums = [2,5,6,8,5], k = 4
//
//输出：2
//
//解释：我们将 nums[1] 和 nums[4] 减 1 得到 [2, 4, 6, 8, 4] 。现在数组的中位数等于 k 。
//
//示例 2：
//
//输入：nums = [2,5,6,8,5], k = 7
//
//输出：3
//
//解释：我们将 nums[1] 增加 1 两次，并且将 nums[2] 增加 1 一次，得到 [2, 7, 7, 8, 5] 。
//
//示例 3：
//
//输入：nums = [1,2,3,4,5,6], k = 4
//
//输出：0
//
//解释：数组中位数已经等于 k 了。
//
//
//
//提示：
//
//1 <= nums.length <= 2 * 105
//1 <= nums[i] <= 109
//1 <= k <= 109

// 贪心
func minOperationsToMakeMedianK(nums []int, k int) int64 {
	abs := func(a int) int {
		if a < 0 {
			return -a
		} else {
			return a
		}
	}
	n := len(nums)
	sort.Ints(nums)
	mid := n / 2
	// 计算中位数需要修改的次数
	var res int64
	res += int64(abs(k - nums[mid]))
	// 计算左边需要调整的数量
	nums[mid] = k
	for i := mid - 1; i >= 0; i-- {
		if nums[i] <= nums[i+1] {
			break
		}
		res += int64(nums[i] - nums[i+1])
		nums[i] = nums[i+1]
	}
	// 调整右边
	for i := mid + 1; i < n; i++ {
		if nums[i] >= nums[i-1] {
			break
		}
		res += int64(nums[i-1] - nums[i])
		nums[i] = nums[i-1]
	}
	return res
}
