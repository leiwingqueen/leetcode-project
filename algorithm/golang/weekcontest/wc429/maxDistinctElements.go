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
		if nums[l]-k >= minVal {
			minVal = nums[l] - k + 1
			cnt++
		} else if nums[l]+k >= minVal {
			minVal++
			cnt++
		}
		l++
		if l > r || minVal > maxVal {
			break
		}
		// 右边界处理
		// [nums[r]-k,nums[r]+k]
		if nums[r]+k <= maxVal {
			maxVal = nums[r] + k - 1
			cnt++
		} else if nums[r]-k <= maxVal {
			maxVal--
			cnt++
		}
		r--
	}
	return cnt
}
