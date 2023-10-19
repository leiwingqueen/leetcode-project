package hash

import "sort"

func tupleSameProduct(nums []int) int {
	n := len(nums)
	res := 0
	mp := make(map[int]bool)
	for _, num := range nums {
		mp[num] = true
	}
	sort.Ints(nums)
	for p1 := 0; p1 <= n-3; p1++ {
		for p2 := n - 1; p2 >= p1+3; p2-- {
			s := nums[p1] * nums[p2]
			for p3 := p1 + 1; p3 < p2-1; p3++ {
				if s%nums[p3] == 0 && mp[s/nums[p3]] {
					res++
				}
			}
		}
	}
	return res * 8
}
