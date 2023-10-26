package bwc113

// 贪心，错误的
func minLengthAfterRemovals(nums []int) int {
	l, r := 0, len(nums)-1
	for l < r {
		if nums[l] != nums[r] {
			l++
			r--
		} else {
			return r - l + 1
		}
	}
	if l == r {
		return 1
	} else {
		return 0
	}
}
