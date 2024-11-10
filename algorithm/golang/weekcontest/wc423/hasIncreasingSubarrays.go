package wc423

func hasIncreasingSubarrays(nums []int, k int) bool {
	n := len(nums)
	check := func(start int) bool {
		for i := start + 1; i < start+k; i++ {
			if nums[i] <= nums[i-1] {
				return false
			}
		}
		for i := start + k + 1; i < start+2*k; i++ {
			if nums[i] <= nums[i-1] {
				return false
			}
		}
		return true
	}
	for i := 0; i <= n-2*k; i++ {
		if check(i) {
			return true
		}
	}
	return false
}
