package wc486

func minimumPrefixLength(nums []int) int {
	n := len(nums)
	i := n - 2
	for i >= 0 {
		if nums[i] < nums[i+1] {
			i--
		} else {
			break
		}
	}
	// 删掉[0,i]
	return i + 1
}
