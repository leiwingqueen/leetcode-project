package wc398

func isArraySpecial(nums []int) bool {
	if len(nums) == 1 {
		return true
	}
	for i := 1; i < len(nums); i++ {
		if nums[i-1]%2 == nums[i]%2 {
			return false
		}
	}
	return true
}
