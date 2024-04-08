package wc392

func longestMonotonicSubarray(nums []int) int {
	n := len(nums)
	l, r := 0, 0
	res := 0
	for r < n {
		if l == r || nums[r] > nums[r-1] {
			r++
			res = max(res, r-l)
		} else {
			l = r
		}
	}
	l, r = 0, 0
	for r < n {
		if l == r || nums[r] < nums[r-1] {
			r++
			res = max(res, r-l)
		} else {
			l = r
		}
	}
	return res
}
