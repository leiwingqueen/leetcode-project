package wc371

func maximumStrongPairXor(nums []int) int {
	abs := func(a int) int {
		if a < 0 {
			return -a
		} else {
			return a
		}
	}
	min := func(a, b int) int {
		if a < b {
			return a
		} else {
			return b
		}
	}
	n := len(nums)
	res := 0
	for i := 0; i < n; i++ {
		for j := i; j < n; j++ {
			sub := abs(nums[i] - nums[j])
			if sub <= min(nums[i], nums[j]) && (nums[i]^nums[j]) > res {
				res = nums[i] ^ nums[j]
			}
		}
	}
	return res
}
