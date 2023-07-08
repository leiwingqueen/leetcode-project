package bwc108

func alternatingSubarray(nums []int) int {
	check := func(i, j int) bool {
		s0, s1 := nums[i], nums[i+1]
		if s1-s0 != 1 {
			return false
		}
		for k := 2; k < j-i+1; k++ {
			if k%2 == 0 {
				if nums[i+k]-nums[i+k-1] != -1 {
					return false
				}
			} else {
				if nums[i+k]-nums[i+k-1] != 1 {
					return false
				}
			}
		}
		return true
	}
	n := len(nums)
	res := -1
	for i := 0; i < n-1; i++ {
		for j := i + 1; j < n; j++ {
			if check(i, j) && j-i+1 > res {
				res = j - i + 1
			}
		}
	}
	return res
}
