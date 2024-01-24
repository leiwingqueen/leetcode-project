package bwc122

import (
	"math"
	"math/bits"
	"sort"
)

// 给你一个下标从 0 开始且全是 正 整数的数组 nums 。
//
//一次 操作 中，如果两个 相邻 元素在二进制下数位为 1 的数目 相同 ，那么你可以将这两个元素交换。你可以执行这个操作 任意次 （也可以 0 次）。
//
//如果你可以使数组变有序，请你返回 true ，否则返回 false 。
//
//
//
//示例 1：
//
//输入：nums = [8,4,2,30,15]
//输出：true
//解释：我们先观察每个元素的二进制表示。 2 ，4 和 8 分别都只有一个数位为 1 ，分别为 "10" ，"100" 和 "1000" 。15 和 30 分别有 4 个数位为 1 ："1111" 和 "11110" 。
//我们可以通过 4 个操作使数组有序：
//- 交换 nums[0] 和 nums[1] 。8 和 4 分别只有 1 个数位为 1 。数组变为 [4,8,2,30,15] 。
//- 交换 nums[1] 和 nums[2] 。8 和 2 分别只有 1 个数位为 1 。数组变为 [4,2,8,30,15] 。
//- 交换 nums[0] 和 nums[1] 。4 和 2 分别只有 1 个数位为 1 。数组变为 [2,4,8,30,15] 。
//- 交换 nums[3] 和 nums[4] 。30 和 15 分别有 4 个数位为 1 ，数组变为 [2,4,8,15,30] 。
//数组变成有序的，所以我们返回 true 。
//注意我们还可以通过其他的操作序列使数组变得有序。
//示例 2：
//
//输入：nums = [1,2,3,4,5]
//输出：true
//解释：数组已经是有序的，所以我们返回 true 。
//示例 3：
//
//输入：nums = [3,16,8,4,2]
//输出：false
//解释：无法通过操作使数组变为有序。
//
//
//提示：
//
//1 <= nums.length <= 100
//1 <= nums[i] <= 28

// 理解错题目了，题目是相邻的元素
func canSortArray(nums []int) bool {
	mp := make(map[int][]int)
	for i, num := range nums {
		bit := bits.OnesCount(uint(num))
		mp[bit] = append(mp[bit], i)
	}
	sort.Slice(nums, func(i, j int) bool {
		c1 := bits.OnesCount(uint(nums[i]))
		c2 := bits.OnesCount(uint(nums[j]))
		if c1 == c2 {
			return nums[i] < nums[j]
		} else {
			return i < j
		}
	})
	for i := 1; i < len(nums); i++ {
		if nums[i] < nums[i-1] {
			return false
		}
	}
	return true
}

func canSortArray2(nums []int) bool {
	n := len(nums)
	l, r := 0, 0
	preMax := 0
	curMin, curMax := math.MaxInt32, 0
	for r < n {
		if bits.OnesCount(uint(nums[r])) == bits.OnesCount(uint(nums[l])) {
			if nums[r] < curMin {
				curMin = nums[r]
			}
			if nums[r] > curMax {
				curMax = nums[r]
			}
			r++
		} else {
			// 跟上一个区间判断
			if curMin < preMax {
				return false
			}
			preMax = curMax
			l = r
			curMin, curMax = nums[r], nums[r]
		}
	}
	return curMin >= preMax
}
