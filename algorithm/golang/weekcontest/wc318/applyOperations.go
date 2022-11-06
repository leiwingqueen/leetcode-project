package wc318

func applyOperations(nums []int) []int {
	n := len(nums)
	for i := 0; i < n-1; i++ {
		if nums[i] == nums[i+1] {
			nums[i] = 2 * nums[i]
			nums[i+1] = 0
		}
	}
	p1 := 0
	for i := 0; i < n; i++ {
		if nums[i] != 0 {
			nums[p1] = nums[i]
			p1++
		}
	}
	for p1 < n {
		nums[p1] = 0
		p1++
	}
	return nums
}
