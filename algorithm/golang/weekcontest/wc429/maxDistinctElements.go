package wc429

import "sort"

// 贪心，双指针
func maxDistinctElements(nums []int, k int) int {
	n := len(nums)
	sort.Ints(nums)
	l, r := 0, n-1
	minVal, maxVal := nums[l]-k, nums[r]+k
	cnt := 0
	for l <= r {
		if minVal > maxVal {
			break
		}
		// 左边界处理
		if nums[l]+k >= minVal && nums[l]-k <= maxVal {
			minVal = max(nums[l]-k, minVal) + 1
			cnt++
		}
		l++
		if l > r || minVal > maxVal {
			break
		}
		// 右边界处理
		// [nums[r]-k,nums[r]+k]
		if nums[r]+k >= minVal && nums[r]-k <= maxVal {
			maxVal = min(nums[r]+k, maxVal) - 1
			cnt++
		}
		r--
	}
	return cnt
}
