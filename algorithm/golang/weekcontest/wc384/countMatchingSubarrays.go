package wc384

func countMatchingSubarrays(nums []int, pattern []int) int {
	n := len(nums)
	m := len(pattern)
	check := func(start int) bool {
		for i := 0; i < m; i++ {
			if pattern[i] == 1 {
				if nums[start+i+1] <= nums[start+i] {
					return false
				}
			} else if pattern[i] == 0 {
				if nums[start+i+1] != nums[start+i] {
					return false
				}
			} else {
				if nums[start+i+1] >= nums[start+i] {
					return false
				}
			}
		}
		return true
	}
	// [n-m-1,n)
	res := 0
	for i := 0; i <= n-m-1; i++ {
		if check(i) {
			res++
		}
	}
	return res
}
