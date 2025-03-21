package wc441

import "sort"

func maxSum(nums []int) int {
	if len(nums) == 1 {
		return nums[0]
	}
	maxNum := nums[0]
	mp := make(map[int]int)
	for _, num := range nums {
		mp[num]++
		maxNum = max(maxNum, num)
	}
	res := 0
	choose := false
	for k := range mp {
		if k > 0 {
			res += k
			choose = true
		}
	}
	if choose {
		return res
	} else {
		sort.Ints(nums)
		return maxNum
	}
}
