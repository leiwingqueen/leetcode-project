package wc320

func unequalTriplets(nums []int) int {
	n := len(nums)
	res := 0
	for i := 0; i < n-2; i++ {
		for j := i + 1; j < n-1; j++ {
			for k := j + 1; k < n; k++ {
				if nums[i] != nums[j] && nums[i] != nums[k] && nums[j] != nums[k] {
					res++
				}
			}
		}
	}
	return res
}
