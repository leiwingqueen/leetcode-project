package wc412

func getFinalState(nums []int, k int, multiplier int) []int {
	for i := 0; i < k; i++ {
		minIdx := 0
		for j := range nums {
			if nums[j] < nums[minIdx] {
				minIdx = j
			}
		}
		nums[minIdx] *= multiplier
	}
	return nums
}
