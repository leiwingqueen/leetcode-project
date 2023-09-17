package wc363

import "sort"

// 超时
func countWays(nums []int) int {
	n := len(nums)
	check := func(k int) bool {
		cnt := 0
		for _, num := range nums {
			if num == k {
				return false
			}
			if cnt > k {
				return false
			}
			if k > num {
				cnt++
			}
		}
		return cnt == k
	}
	res := 0
	for k := 0; k <= n; k++ {
		if check(k) {
			res++
		}
	}
	return res
}

func countWays2(nums []int) int {
	n := len(nums)
	sort.Ints(nums)
	check := func(k int) bool {
		idx := sort.Search(n, func(i int) bool {
			return nums[i] > k
		})
		return idx == k && ((idx > 0 && nums[idx-1] != k) || idx == 0)
	}
	res := 0
	for k := 0; k <= n; k++ {
		if check(k) {
			res++
		}
	}
	return res
}
