package wc312

import "sort"

func goodIndices(nums []int, k int) []int {
	s1 := make([]int, 0)
	res := make(map[int]struct{})
	for i, num := range nums {
		if len(s1) >= k && s1[len(s1)-k] == i-k {
			res[i] = struct{}{}
		}
		for len(s1) > 0 && nums[s1[len(s1)-1]] < num {
			s1 = s1[0 : len(s1)-1]
		}
		s1 = append(s1, i)
	}
	s2 := make([]int, 0)
	for i := len(nums) - 1; i >= 0; i-- {
		if len(s2) < k || s2[len(s2)-k] != i+k {
			delete(res, i)
		}
		num := nums[i]
		for len(s2) > 0 && nums[s2[len(s2)-1]] < num {
			s2 = s2[0 : len(s2)-1]
		}
		s2 = append(s2, i)
	}
	r := make([]int, 0)
	for i := range res {
		r = append(r, i)
	}
	sort.Ints(r)
	return r
}
