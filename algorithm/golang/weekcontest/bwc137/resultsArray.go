package bwc137

func resultsArray(nums []int, k int) []int {
	n := len(nums)
	l, r := 0, 0
	unmatch := 0
	res := make([]int, n-k+1)
	for r < n {
		if r-l < k {
			if r > l && nums[r] <= nums[r-1] {
				unmatch++
			}
			r++
		} else {
			res[l] = -1
			if unmatch == 0 {
				res[l] = nums[r-1]
			}
			if nums[l+1] <= nums[l] {
				unmatch--
			}
			l++
		}
	}
	res[l] = -1
	if unmatch == 0 {
		res[l] = nums[r-1]
	}
	return res
}
