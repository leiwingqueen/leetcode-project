package wc424

import "sort"

func minZeroArray2(nums []int, queries [][]int) int {
	n := len(nums)
	// 二分查找即可
	check := func(k int) bool {
		diff := make([]int, n)
		diff[0] = nums[0]
		for i := 1; i < n; i++ {
			diff[i] = nums[i] - nums[i-1]
		}
		for i := 0; i < k; i++ {
			query := queries[i]
			l, r, v := query[0], query[1], query[2]
			diff[l] -= v
			if r < n-1 {
				diff[r+1] += v
			}
		}
		// 还原数组
		pre := diff[0]
		if pre > 0 {
			return false
		}
		for i := 1; i < n; i++ {
			cur := pre + diff[i]
			if cur > 0 {
				return false
			}
			pre = cur
		}
		return true
	}
	k := sort.Search(len(queries)+1, func(i int) bool {
		return check(i)
	})
	if k > len(queries) {
		return -1
	} else {
		return k
	}
}
