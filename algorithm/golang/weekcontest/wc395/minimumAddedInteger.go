package wc395

import (
	"math"
	"sort"
)

func minimumAddedInteger(nums1 []int, nums2 []int) int {
	n := len(nums1)
	sort.Ints(nums1)
	sort.Ints(nums2)
	check := func(i, j int) (bool, int) {
		p1, p2 := 0, 0
		for p1 == i || p1 == j {
			p1++
		}
		diff := nums2[p2] - nums1[p1]
		for p1 < n {
			if p1 != i && p1 != j {
				if nums2[p2] != nums1[p1]+diff {
					return false, 0
				}
				p2++
			}
			p1++
		}
		return true, diff
	}
	res := math.MaxInt32
	for i := 0; i < n; i++ {
		for j := i + 1; j < n; j++ {
			ok, diff := check(i, j)
			if ok {
				res = min(res, diff)
			}
		}
	}
	return res
}
